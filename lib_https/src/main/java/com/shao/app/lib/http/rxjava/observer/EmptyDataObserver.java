package com.shao.app.lib.http.rxjava.observer;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/7/1
 */
public abstract class EmptyDataObserver<T> extends BaseObserver<T> {
    @Override
    public void onComplete() {
        super.onComplete();
        onSuccess();
    }

    public abstract void onSuccess();
}
