package cn.zhang.net_work.net.nethelper;

import io.reactivex.disposables.Disposable;

/**
 * Author: kuencheung
 * Date:    2018/6/21
 * Des:     请求还没有结束的时候，View在没有执行完毕就退出了，那么它肯定不会自动解绑，
 *          这个时候就需要我们手动解除订阅关系，否则会产生内存泄漏
 */

public interface SubscriptionHelper {
    void add(Disposable subscription);

    void cancel(Disposable t);

    void cancelall();

}
