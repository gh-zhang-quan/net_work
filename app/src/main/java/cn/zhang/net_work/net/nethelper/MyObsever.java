package cn.zhang.net_work.net.nethelper;

import cn.zhang.net_work.constant.StaticConfig;
import cn.zhang.net_work.utils.MyLog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author: kuencheung
 * Date:   2018/6/21
 * Des:    订阅事件关系,统一处理后台返回数据
 */

public abstract class MyObsever<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        SubscriptionManager.getInstance().add(d);
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            OnFail("数据为空");
        } else {
            OnSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        //自定义异常的传递
        onComplete();
        String fail = ExceptionHandle.handleException(e);
        MyLog.e(StaticConfig.LOG_THROWABLE, e.toString(), true);
        OnFail(fail);
    }

    protected abstract void OnSuccess(T t);

    protected abstract void OnFail(String fail);

    public void onComplete() {
    }

}
