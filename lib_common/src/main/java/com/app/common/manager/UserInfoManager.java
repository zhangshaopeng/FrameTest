package com.app.common.manager;

import android.text.TextUtils;

import com.app.common.module.login.bean.UserInfo;
import com.shao.app.utils.GsonUtils;
import com.shao.app.utils.SPUtils;


/**
 * <p>Description:
 * <p>Company:汇桔网
 * <p>Email:sundewu@wtoip.com
 * <p>@author:Created by Devin Sun on 2018/1/4.
 */
public class UserInfoManager {

    private static final String USER_INFO_KEY = "userInfo";


    private static UserInfo userInfo;

    private static UserInfoManager ourInstance = new UserInfoManager();

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {
        return ourInstance;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(getUserToken());
    }

    public String getAvatar() {
        return getUserInfo().getAvatar();
    }

    public String getUserToken() {
        return getUserInfo().getToken();
    }

    public String getUserName() {
        return getUserInfo().getLoginName();
    }

    public String getTelNum() {
        return getUserInfo().getPhone();
    }

    public synchronized void saveUserInfo(UserInfo userInfo) {
        UserInfoManager.userInfo = userInfo;
        SPUtils.putParam(USER_INFO_KEY, GsonUtils.getJsonByObj(userInfo));
    }

    public synchronized void cleanUserInfo() {
        saveUserInfo(new UserInfo());
    }


    public synchronized UserInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = GsonUtils.getObject((String) SPUtils.getParam(USER_INFO_KEY, ""), UserInfo.class);
            if (userInfo == null) {
                userInfo = new UserInfo();
            }
        }
        return userInfo;
    }


}
