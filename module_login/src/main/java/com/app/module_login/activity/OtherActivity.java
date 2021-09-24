package com.app.module_login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.app.common.activity.BaseActivity;
import com.app.module_login.R;

/**
 * p>Describe:
 * p>Company:Dyne
 * p>@Author:zsp
 * p>Data:2019/2/14.
 */
public class OtherActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_login_activity_four);
        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        setResult(100);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv) {

        } else {

        }
    }
}
