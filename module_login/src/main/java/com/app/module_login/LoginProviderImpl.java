package com.app.module_login;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.common.UserInfoManager;
import com.app.common.module.provider.LoginProvider;
import com.app.common.module.provider.LoginProviderManager;
import com.shao.app.utils.AppInfo;

/**
 * <p>Description:
 * <p>Company:汇桔网
 * <p>Email:sundewu@wtoip.com
 * <p>@author:Created by Devin Sun on 2018/4/24.
 */
@Route(path = LoginProviderManager.LOGIN_PROVIDER_URI)
public class LoginProviderImpl implements LoginProvider {

    @Override
    public void init(Context context) {
    }

    @Override
    public void initModule(Context context, boolean debug) {
        // 初始化操作
        if (AppInfo.isMainProcess()) {
            String token = UserInfoManager.getInstance().getUserInfo().getToken();
//            PushSetManager.initPush(context, token);
        }
    }
}
