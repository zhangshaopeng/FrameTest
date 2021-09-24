package com.app.common.module.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Description:
 * Company:HuiJu
 * author: ZhangShaoPeng
 * Data: 2018/4/4
 */
public interface LoginProvider extends IProvider {

    void initModule(Context context, boolean debug);

}
