package cn.zhang.net_work.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

/**
 * Author: Jack_zhang_quan
 * Email:  zhang_quan_888@163.com
 * Date:   2019/3/2
 * Explain:Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private AppCompatDelegate mDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setContentView(initLayout());
        initView(savedInstanceState);
        initData();
    }

    @NonNull
    public AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            //第一次为空，创建了AppCompatDelegate
            mDelegate = AppCompatDelegate.create(this, this);
        }
        return mDelegate;
    }

    //初始化页面
    protected abstract int initLayout();

    //初始化页面布局
    protected abstract void initView(Bundle savedInstanceState);

    //初始化数据
    protected abstract void initData();

}
