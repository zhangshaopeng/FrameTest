package com.shao.app.lib.http.rxjava.observer;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/7/1
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

}
