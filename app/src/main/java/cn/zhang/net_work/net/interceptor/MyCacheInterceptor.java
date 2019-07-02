package cn.zhang.net_work.net.interceptor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.io.IOException;

import cn.zhang.net_work.base.BaseApplication;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: kuencheung
 * Date:   2018/8/7
 * Des:    设置缓存
 */
public class MyCacheInterceptor implements Interceptor {
    private static NetworkInfo current;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        //设置缓存机制
        if (isNetworkReachable(BaseApplication.mContext)) {
            request = request.newBuilder()
                    //网络可用 强制从网络获取数据
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        } else {
            request = request.newBuilder()
                    //网络不可用 从缓存获取
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        if (isNetworkReachable(BaseApplication.mContext)) {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    // 有网络时 设置缓存超时时间1个小时
                    .header("Cache-Control", "public, max-age=" + 60 * 60)
                    .build();
        } else {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    // 无网络时，设置超时为4周
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 7 * 24 * 60 * 60 * 4)
                    .build();
        }

        return response;
    }

    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    private static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) current = cm.getActiveNetworkInfo();
        return current != null && (current.isConnected());
    }
}
