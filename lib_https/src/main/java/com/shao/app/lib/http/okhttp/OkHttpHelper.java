package com.shao.app.lib.http.okhttp;


import com.shao.app.lib.http.manager.HttpManager;
import com.shao.app.lib.http.okhttp.interceptor.HeaderInterceptor;
import com.shao.app.lib.http.okhttp.interceptor.PrintLoggingInterceptor;
import com.shao.app.lib.http.okhttp.interceptor.WrapperInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;



/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/3/12
 */
public class OkHttpHelper {

    /**
     * 连接超时
     */
    private static final int CONNECT_TIMEOUT = 10;
    /**
     * 读取超时
     */
    private static final int READ_TIMEOUT = 25;
    /**
     * 写入超时
     */
    private static final int WRITE_TIMEOUT = 25;
    private static OkHttpClient okHttpClient;

    static {

//        CustomHttpsTrust customHttpsTrust = new CustomHttpsTrust();
//        CustomHttpsTrust customHttpsTrust = new CustomHttpsTrust(CertificateStreamFactory.trustedCertificatesInputStream("xxx"));

//
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);

//        builder.addInterceptor(new HttpLoggingInterceptor());
        if (HttpManager.isDebug()) {
            builder.addInterceptor(new PrintLoggingInterceptor(false, true)); //请求信息的打印 ，可在 release 时关闭
        }
        builder.addInterceptor(new WrapperInterceptor());
        builder.addInterceptor(new HeaderInterceptor()); //添加 header
        if (HttpManager.isDebug()) {
            builder.addInterceptor(new PrintLoggingInterceptor(true, false)); //请求信息的打印 ，可在 release 时关闭
        }
//                .sslSocketFactory(customHttpsTrust.sSLSocketFactory, customHttpsTrust.x509TrustManager)// https 配置
        okHttpClient = builder.build();
    }

    private OkHttpHelper() {
    }

    public static OkHttpClient getClient() {
        return okHttpClient;
    }

}
