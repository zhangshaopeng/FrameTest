package com.app.common.module.login.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/6/19
 */
public class LoginModuleManager {

    public static void startLoginActivity(Context context) {
        ARouter.getInstance().build(LoginModuleUriList.LOGIN_ACTIVITY_URI)
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .navigation(context);
    }

    public static void startForResultLoginActivity(Context context) {
        ARouter.getInstance().build(LoginModuleUriList.RESULT_ACTIVITY_URI)
//                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .withString("str", "123")
                .navigation(context);
    }

    public static void startOnForResultLoginActivity(Activity activity) {
        ARouter.getInstance().build(LoginModuleUriList.ONACTIVITYRESULT_ACTIVITY_URI)
//                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                .withString("str", "123")
                .navigation(activity, 100);
    }
}
