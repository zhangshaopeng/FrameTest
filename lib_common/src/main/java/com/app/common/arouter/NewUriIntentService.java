package com.app.common.arouter;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * p>Describe:
 * p>Company:Dyne
 * p>@Author:zsp
 * p>Data:2019/2/11.
 */
public class NewUriIntentService extends IntentService {


    public NewUriIntentService() {
        super("NewUriIntentService");
    }

    /**
     * @param context
     */
    public static void startService(Context context) {
        Intent intent = new Intent(context, NewUriIntentService.class);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO:  zhangshaopeng  请求后台最新的 uri 映射表
    }

}