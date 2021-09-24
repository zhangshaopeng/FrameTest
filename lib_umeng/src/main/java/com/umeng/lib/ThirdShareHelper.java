package com.umeng.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * <p>Description:
 * <p>Company:汇桔网
 * <p>Email:sundewu@wtoip.com
 * <p>@author:Created by Devin Sun on 2018/3/6.
 */
public class ThirdShareHelper {

    public static void share(final Activity activity, String url, String title, String thumbUrl, String desc, SHARE_MEDIA share_media) {

        //当设备没有安装QQ时
        if (SHARE_MEDIA.QQ == share_media && !PlatformUtil.isInstallQQ(activity.getApplication())) {
            Toast.makeText(activity, "授权失败，请检查是否安装QQ", Toast.LENGTH_SHORT).show();
            return;
        } else if (SHARE_MEDIA.WEIXIN == share_media && !PlatformUtil.isInstallWeChat(activity.getApplication())) {
            //当设备没有安装wx时
            Toast.makeText(activity, "授权失败，请检查是否安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        UMImage image;
        if (TextUtils.isEmpty(thumbUrl)) {
            image = new UMImage(activity, R.mipmap.huiju);
        } else {
            image = new UMImage(activity.getApplicationContext(), thumbUrl);//网络图片
        }
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(desc);//描述

        new ShareAction(activity)
                .withMedia(web)
                .setPlatform(share_media)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(activity, "分享成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                        if (share_media == SHARE_MEDIA.WEIXIN) {
                            Toast.makeText(activity, "分享失败，请检查是否安装微信", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "分享失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Toast.makeText(activity, "分享已取消", Toast.LENGTH_SHORT).show();
                    }
                }).share();
    }

    public static void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        //友盟第三方登录的破玩意
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

    public static void destroy(Activity activity) {
        UMShareAPI.get(activity).release();
    }
}
