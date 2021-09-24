package com.umeng.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * <p>Description:
 * <p>Company:汇桔网
 * <p>Email:sundewu@wtoip.com
 * <p>@author:Created by Devin Sun on 2018/3/6.
 */
public class ThirdLoginHelper {

    public static void login(Activity activity, SHARE_MEDIA shareMedia, UMAuthListener umAuthListener) {

        //当设备没有安装QQ时
        if (SHARE_MEDIA.QQ == shareMedia && !PlatformUtil.isInstallQQ(activity.getApplication())) {
            Toast.makeText(activity, "授权失败，请检查是否安装QQ", Toast.LENGTH_SHORT).show();
            return;
        } else if (SHARE_MEDIA.WEIXIN == shareMedia && !PlatformUtil.isInstallWeChat(activity.getApplication())) {
            //当设备没有安装wx时
            Toast.makeText(activity, "授权失败，请检查是否安装微信", Toast.LENGTH_SHORT).show();
            return;
        }

        UMShareAPI.get(activity.getApplication()).getPlatformInfo(activity, shareMedia, umAuthListener);
    }


    public static void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        //友盟第三方登录的破玩意
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }
}
