package com.app.module_login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.common.module.login.router.LoginModuleUriList;
import com.app.module_login.R;



@Route(path = LoginModuleUriList.ONACTIVITYRESULT_ACTIVITY_URI)
public class OnActivityResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_login_activity_on_result);
        String str = getIntent().getStringExtra("str");
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(2010);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
