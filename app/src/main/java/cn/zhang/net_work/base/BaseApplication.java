package cn.zhang.net_work.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Author: Jack_zhang_quan
 * Email:  zhang_quan_888@163.com
 * Date:   2019/3/2
 * Explain:
 */
public class BaseApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    @Override
    @Deprecated
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
