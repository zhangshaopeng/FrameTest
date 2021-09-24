package com.app.common.arouter.interceptor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.app.common.arouter.RouterManager;


/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/6/19
 */
@Interceptor(priority = 1)
public class RouterInterceptor implements IInterceptor {

    private Context mContext;


    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();

        String newPath = RouterManager.getNewUriMap().get(path);
        if (!TextUtils.isEmpty(newPath)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newPath));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            callback.onInterrupt(null);
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
