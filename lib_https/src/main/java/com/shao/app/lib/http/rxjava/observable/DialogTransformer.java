package com.shao.app.lib.http.rxjava.observable;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.shao.app.lib.http.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/4/1
 */
public class DialogTransformer {

    private static final String DEFAULT_MSG = "数据加载中...";

    private Activity activity;
    private String msg;
    private boolean cancelable;

    public DialogTransformer(Activity activity) {
        this(activity, DEFAULT_MSG);
    }

    public DialogTransformer(Activity activity, String msg) {
        this(activity, msg, false);
    }

    public DialogTransformer(Activity activity, String msg, boolean cancelable) {
        this.activity = activity;
        this.msg = msg;
        this.cancelable = cancelable;
    }

    public <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {
            private Dialog progressDialog;

            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {

                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull final Disposable disposable) throws Exception {
                        progressDialog = new Dialog(activity, R.style.progress_dialog);
                        progressDialog.setContentView(R.layout.dialog);
                        progressDialog.setCancelable(true);
                        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        ImageView image_loading = progressDialog.findViewById(R.id.image_loading);
                        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.loading);
                        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
                        animation.setInterpolator(lin);
                        image_loading.startAnimation(animation);
                        TextView loadText = progressDialog.findViewById(R.id.id_tv_loadingmsg);
                        loadText.setText(DEFAULT_MSG);
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();
                        if (cancelable) {
                            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    disposable.dispose();
                                }
                            });
                        }
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                    }
                });
            }
        };
    }
}
