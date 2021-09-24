package com.app.common.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;


public class SchemeFilterActivity extends AppCompatActivity {

    private static final String TAG = SchemeFilterActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (uri != null) {
            Log.e(TAG, "scheme: " + uri.getScheme());
            Log.e(TAG, "host: " + uri.getHost());
            Log.e(TAG, "port: " + uri.getPort());
            Log.e(TAG, "path: " + uri.getPath());
            Log.e(TAG, "queryString: " + uri.getQuery());

            String type = uri.getQueryParameter("type");
            String targetUri = uri.getQueryParameter("uri");
            if (TextUtils.equals(type, "1")) {
                ARouter.getInstance().build(targetUri).withFlags(Intent.FLAG_ACTIVITY_NEW_TASK).navigation();
            } else if (TextUtils.equals(type, "2")) {
//                PubManager.startWebViewActivity(this, targetUri);
            }

        } else {
            Toast.makeText(this, "操作异常，请重试", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
