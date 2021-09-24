package com.app.module_login.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.common.activity.BaseActivity;
import com.app.common.module.login.router.LoginModuleUriList;
import com.app.module_login.R;




@Route(path = LoginModuleUriList.LOGIN_ACTIVITY_URI)
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_login_activity_main);
    }
}
