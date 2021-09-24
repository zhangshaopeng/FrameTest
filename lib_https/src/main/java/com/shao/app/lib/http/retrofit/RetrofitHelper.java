package com.shao.app.lib.http.retrofit;

import com.shao.app.lib.http.okhttp.OkHttpHelper;
import com.shao.app.lib.http.retrofit.converter.string.StringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:初始化Retrofit
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/3/13
 */

public class RetrofitHelper {


    public static final String BASE_URL_BJ = "https://bjopenapi.wtoip.com";

    public static final String BASE_URL_M = "https://m.wtoip.com";


    private static Retrofit retrofit;

    private RetrofitHelper() {
    }

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitHelper.BASE_URL_BJ)
                .client(OkHttpHelper.getClient())
                .addConverterFactory(StringConverterFactory.create()) //String 转换
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .validateEagerly(true)
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
