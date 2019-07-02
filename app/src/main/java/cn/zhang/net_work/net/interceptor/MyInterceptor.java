package cn.zhang.net_work.net.interceptor;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import cn.zhang.net_work.constant.StaticConfig;
import cn.zhang.net_work.utils.MyLog;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * Author: kuencheung
 * Date:   2018/7/11
 * Des:    设置日志拦截器
 */
public class MyInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                //此处添加请求头
                .build();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取调用方法------>获取连接地址url
        MyLog.d(StaticConfig.LOG_HTTPINTERCEPTOR, "请求方法和地址：" +
                request.method() + " ：" + request.url().toString(), true);

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            StringBuilder sb = new StringBuilder("Request Body [");
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            } else {
                if (response != null) {
                    ResponseBody body = response.body();
                    if (body != null)
                        contentType = body.contentType();
                    if (contentType != null)
                        charset = contentType.charset(charset);
                }
            }
            if (contentType != null)
                if (isPlaintext(buffer)) {
                    if (charset != null)
                        sb.append(buffer.readString(charset));
                    sb.append(" (Content-Type = ").append(contentType.toString()).append(",")
                            .append(requestBody.contentLength()).append("-byte body)");
                } else {
                    sb.append(" (Content-Type = ").append(contentType.toString()).append(",binary ")
                            .append(requestBody.contentLength()).append("-byte body omitted)");
                }
            sb.append("]");
            //传递给后台的参数
            MyLog.d(StaticConfig.LOG_HTTPINTERCEPTOR, "请求参数：" + sb.toString(), true);
        }

        //获取后台返回数据Json
        @SuppressLint({"NewApi", "LocalSuppress"})
        ResponseBody body = Objects.requireNonNull(response).body();
        if (body != null) {
            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            if (charset != null)
                MyLog.json(StaticConfig.LOG_HTTPINTERCEPTOR, buffer.clone().readString(charset));
        }

        return response;
    }

    private static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }

                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
