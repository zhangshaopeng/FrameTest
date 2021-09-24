package com.umeng.lib;

import android.content.Context;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.common.SocializeConstants;

/**
 * <p>Description:
 * <p>Company:汇桔网
 * <p>Email:sundewu@wtoip.com
 * <p>@author:Created by Devin Sun on 2018/1/11.
 */
public class UMengManager {

    /**
     * 初始化umeng及第三方
     *
     * @param context
     * @param debug
     */
    public static void init(Context context, boolean debug) {


        UMConfigure.setLogEnabled(debug);
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setCatchUncaughtExceptions(!debug);
        //推送
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        config.isOpenShareEditActivity(true);
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        UMShareAPI.get(context.getApplicationContext()).setShareConfig(config);

        PlatformConfig.setWeixin("wxe00ba4a162384169", "e951c6dc5e34687a3b6391c1b6f84309");
        PlatformConfig.setQQZone("1105258507", "GgCGybgK1m41csl1");
        PlatformConfig.setSinaWeibo("3114388393", "695af2a339d0e5f6fd07f87718d66d2b", "https://api.weibo.com/oauth2/default.html");

        UMConfigure.init(context.getApplicationContext(), "5940aabea3251153c3000655"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "7aedcab0b1a3d96b05cab035aa997a20");

        Log.d("+++++++", "===" + SocializeConstants.SDK_VERSION);

    }


}
