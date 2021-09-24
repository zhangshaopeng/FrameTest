package com.app.common.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.WindowManager;

import com.shao.app.utils.DeviceInfo;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * p>Describe:
 * p>Company:Dyne
 * p>@Author:zsp
 * p>Data:2019/2/11.
 */
public class BaseActivity extends RxAppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //一进入页面，输入法总是隐藏的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setStatusBarColor(getResources().getColor(R.color.bgColor_default));
    }

    @Override
    protected void onResume() {
        String packageName = getApplicationContext().getPackageName();
        if (getCacheDir().exists()) {
            String path = getCacheDir().getAbsolutePath();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 设置透明状态栏并对某个view 拉伸，仅支持4.4及以上
     *
     * @param view
     */
    public void setTranslucentStatus(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            view.setPadding(view.getPaddingLeft(),
                    DeviceInfo.getStatusHeight(this) + view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBottom());
        }
    }

    /***
     * 设置状态栏透明
     */
    public final void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     */
    protected final void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(color);
        }
    }

    /**
     * 隐藏状态栏
     */
    protected final void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }


    public void showProgressDialog() {
        showProgressDialog("数据加载中...");
    }


    public void showProgressDialog(@StringRes int resId) {
        showProgressDialog(getString(resId));
    }

    public void showProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage(msg + "");
        if (!isFinishing()) {
            progressDialog.show();
        }
    }


    public void cancelProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelProgressDialog();
    }

}
