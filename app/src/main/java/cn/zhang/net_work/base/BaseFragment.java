package cn.zhang.net_work.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Jack_zhang_quan
 * Email:  zhang_quan_888@163.com
 * Date:   2019/3/2
 * Explain:Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(BaseApplication.mContext).inflate(getContentViewId(), container, false);
        }
        initView(rootView);
        initData();
        return rootView;
    }

    //加载布局
    protected abstract int getContentViewId();

    //初始化页面布局
    protected abstract void initView(View rootView);

    //初始化数据
    protected abstract void initData();

}
