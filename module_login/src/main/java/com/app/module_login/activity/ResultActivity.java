package com.app.module_login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.common.module.login.router.LoginModuleUriList;
import com.app.module_login.R;
import com.shao.app.utils.ToastUtils;


@Route(path = LoginModuleUriList.RESULT_ACTIVITY_URI)
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_login_activity_result);
        String str = getIntent().getStringExtra("str");
        ToastUtils.show( "携带参数：" + str + "");
    }

}
