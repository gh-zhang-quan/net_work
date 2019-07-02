package cn.zhang.net_work.net.fastjson;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Author：  KuenCheung
 * Email：   zhang_quan_888@163.com
 * Time：    2018/10/30
 * Description：
 */
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(@NonNull T value) {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}
