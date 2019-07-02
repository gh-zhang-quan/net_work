package cn.zhang.net_work.net;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.zhang.net_work.base.BaseApplication;
import cn.zhang.net_work.constant.Constant;
import cn.zhang.net_work.net.fastjson.FastJsonConverterFactory;
import cn.zhang.net_work.net.interceptor.MyCacheInterceptor;
import cn.zhang.net_work.net.interceptor.MyInterceptor;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Author: kuencheung
 * Date:   2018/6/21
 * Des:    RetrofitManager管理器的创建，保证Retrofit在类中只有一个实例，避免请求体的多次创建
 */

public class RetrofitManager {

    private final Retrofit retrofit;
    private final ApiInterface apiInterface;

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public static RetrofitManager getInstance(String url) {
        return new RetrofitManager(url);
    }

    private ApiInterface ApiInterface() {
        return retrofit.create(ApiInterface.class);
    }

    private RetrofitManager(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        apiInterface = ApiInterface();
    }

    private OkHttpClient getOkHttpClient() {
        //设置数据缓存
        File cacheDir = new File(BaseApplication.mContext.getCacheDir(), "HttpCaches");
        //缓存的最大尺寸10M
        Cache cache = new Cache(cacheDir, 1024 * 1024 * 10);
        return new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .addInterceptor(new MyCacheInterceptor())
                .addNetworkInterceptor(new MyCacheInterceptor())
                .cache(cache)
                .connectTimeout(Constant.Net.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.Net.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.Net.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public static <T> Observable<T> requestHandler(Observable<T> response) {
        return response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
