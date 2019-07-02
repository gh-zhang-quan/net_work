package cn.zhang.net_work.net.fastjson;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * Author：  KuenCheung
 * Email：   zhang_quan_888@163.com
 * Time：    2018/10/30
 * Description：
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /**
     * 转换方法
     */
    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        return JSON.parseObject(tempStr, type);

    }
}
