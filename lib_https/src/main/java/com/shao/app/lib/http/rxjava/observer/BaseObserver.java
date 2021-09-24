package com.shao.app.lib.http.rxjava.observer;

import android.os.Handler;
import android.os.Looper;

import com.shao.app.lib.http.manager.HttpManager;
import com.shao.app.lib.http.result.HttpRespException;
import com.shao.app.lib.http.util.Logger;
import com.shao.app.lib.http.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/7/1
 */
public class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        HttpRespException responseException;
        Logger.e("http request error:: " + e.toString());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            responseException = new HttpRespException("网络请求出错", httpException.code(), -1024);
        } else if (e instanceof HttpRespException) {
            responseException = (HttpRespException) e;
        } else {//其他或者没网会走这里
            responseException = new HttpRespException("网络异常,请稍后重试", 0, -1024);
        }

        onFailed(responseException);
    }

    @Override
    public void onComplete() {

    }

    public void onFailed(final HttpRespException responseException) {

        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            showErrorToast(responseException);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showErrorToast(responseException);
                }
            });
        }
    }


    private void showErrorToast(HttpRespException responseException) {
        if (HttpManager.isDebug()) {
            ToastUtils.show(responseException.getMessage() + "(" + responseException.getStatus() + "," + responseException.getCode() + ")");
        } else {
            ToastUtils.show(responseException.getMessage());
        }
    }

}
