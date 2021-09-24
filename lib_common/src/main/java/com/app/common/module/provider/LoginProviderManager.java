package com.app.common.module.provider;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Description:
 * Company:HuiJu
 * author: ZhangShaoPeng
 * Data: 2018/4/4
 */
public class LoginProviderManager {

    public static final String LOGIN_PROVIDER_URI = "/login/LoginProviderImpl";

    public static LoginProvider getProvider() {
        return (LoginProvider) ARouter.getInstance().build(LOGIN_PROVIDER_URI).navigation();
    }

}
