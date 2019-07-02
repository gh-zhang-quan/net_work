package cn.zhang.net_work.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zhang.net_work.net.nethelper.SubscriptionManager;

/**
 * Author:  kuencheung
 * Date:    2018/7/4
 * Des:
 */
@SuppressWarnings("unchecked")
public abstract class BaseMvpFragment<p extends BasePresenter> extends BaseFragment {
    public p presener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        presener = initPresener();
        //把所有继承此类的Activity都绑定到这里了，这样View就和Present联系起来了。
        presener.addView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract p initPresener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        presener.detattch();
        //View消除时取消订阅关系
        SubscriptionManager.getInstance().cancelall();
    }
}
