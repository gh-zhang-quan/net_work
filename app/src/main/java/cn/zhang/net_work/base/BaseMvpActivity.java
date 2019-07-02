package cn.zhang.net_work.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.zhang.net_work.net.nethelper.SubscriptionManager;

/**
 * Author: kuencheung
 * Date:   2018/6/21
 * Des:    BaseMvpActivity<P extends BasePresenter>：p继承了BasePresenter，
 * 就可以用P的参数去调用BasePresenter继承关系
 * #####当activity继承BaseMvpActivity时,需要在initView方法中添加 super.initView(savedInstanceState);
 */
@SuppressWarnings("unchecked")
public abstract class BaseMvpActivity<p extends BasePresenter> extends BaseActivity {
    public p presener;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        presener = initPresener();
        //把所有继承此类的Activity都绑定到这里了，这样View就和Present联系起来了。
        presener.addView(this);
    }

    protected abstract p initPresener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presener.detattch();
        //View消除时取消订阅关系
        SubscriptionManager.getInstance().cancelall();
    }
}
