package com.shao.app.lib.http.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;


/**
 * Description:初始化http
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/2
 */

public class HttpManager {

    private static ConfigProvider configProvider;
    public static Context context;

    public static void setConfigProvider(@NonNull Context context, ConfigProvider configProvider) {
        HttpManager.context=context.getApplicationContext();
        HttpManager.configProvider = configProvider;
    }

    public static String getToken() {
        return configProvider == null ? "" : configProvider.getUserToken();
    }

    public static boolean isLogin() {
        return configProvider != null && !TextUtils.isEmpty(getToken());
    }

    public static boolean isDebug() {
        return configProvider != null && configProvider.isDebug();
    }


    public static boolean isEncrypt() {
        return configProvider != null && configProvider.isEncrypt();
    }


    public interface ConfigProvider {


        /**
         * 用户的token  未登录时可传""或者null，非空时视为登录状态
         *
         * @return
         */
        String getUserToken();

        /**
         * 决定是否打印请求日志,在调试时可置为false
         *
         * @return
         */
        boolean isDebug();

        /**
         * 决定参数是否加解密
         *
         * @return
         */
        boolean isEncrypt();
    }


}
