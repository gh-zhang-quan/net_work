package cn.zhang.net_work.net.nethelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author: kuencheung
 * Date:   2018/6/21
 * Des:
 */

public class SubscriptionManager implements SubscriptionHelper {
    private static SubscriptionManager subscriptionManager;
    private CompositeDisposable mDisposables;

    private SubscriptionManager() {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
    }

    @Override
    public void add(Disposable disposable) {
        if (disposable == null) return;
        mDisposables.add(disposable);
    }

    @Override
    public void cancel(Disposable disposable) {
        if (mDisposables != null) {
            mDisposables.delete(disposable);
        }
    }

    @Override
    public void cancelall() {
        if (mDisposables != null) {
            mDisposables.clear();
        }
    }

    public static SubscriptionManager getInstance() {
        if (subscriptionManager == null) {
            synchronized (SubscriptionManager.class) {
                if (subscriptionManager == null) {
                    subscriptionManager = new SubscriptionManager();
                }
            }
        }
        return subscriptionManager;
    }
}
