package com.shao.app.lib.http.util;

import android.support.annotation.StringRes;
import android.widget.Toast;


import com.shao.app.lib.http.manager.HttpManager;


public class ToastUtils {

    private static Toast toast;

    public static void show(String text) {
        if (toast == null) {
            toast = Toast.makeText(HttpManager.context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    public static void show(@StringRes int resId) {
        show(HttpManager.context.getResources().getString(resId));
    }
    public static void showToast(String text){
        if(toast==null){
            toast = Toast.makeText(HttpManager.context, text, Toast.LENGTH_SHORT);
        }else {
            //如果toast不为空，则直接更改当前toast的文本
            toast.setText(text);
        }
        toast.show();
    }
    public static void showToast(int text){
        if(toast==null){
            toast = Toast.makeText(HttpManager.context, text, Toast.LENGTH_SHORT);
        }else {
            //如果toast不为空，则直接更改当前toast的文本
            toast.setText(text);
        }
        toast.show();
    }
}
