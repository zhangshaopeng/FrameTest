package com.shao.app.lib.http.okhttp.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Description:拦截器：添加请求头
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/4/2
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("user-source", "m.shao.com/app")
                .addHeader("device", "android")
                // add header...
                .build();

        return chain.proceed(request);
    }
}
