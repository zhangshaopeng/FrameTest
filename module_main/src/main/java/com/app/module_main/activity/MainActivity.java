package com.app.module_main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.common.activity.BaseActivity;
import com.app.common.module.login.router.LoginModuleManager;
import com.app.module_main.R;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_main_activity_main);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginModuleManager.startLoginActivity(MainActivity.this);
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginModuleManager.startForResultLoginActivity(MainActivity.this);
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginModuleManager.startOnForResultLoginActivity(MainActivity.this);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2010) {
        }
    }


}
