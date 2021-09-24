package com.shao.app.lib.http.okhttp.interceptor;

import com.shao.app.lib.http.manager.HttpManager;
import com.shao.app.lib.http.retrofit.RetrofitHelper;
import com.shao.app.lib.http.util.ParamsEncrypt;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Description:拦截器：请求重定向
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/4/2
 */

public class WrapperInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        return wrap(chain);
    }


    private Response wrap(Chain chain) throws IOException {

        Request request = chain.request();

        String method = request.method();
        HttpUrl httpUrl = request.url();

        if ("GET".equalsIgnoreCase(method)) {
            //获取到请求地址api
            HashMap<String, Object> rootMap = new HashMap<>();
            //通过请求地址(最初始的请求地址)获取到参数列表
            Set<String> parameterNames = httpUrl.queryParameterNames();
            for (String key : parameterNames) {  //循环参数列表
                String value = httpUrl.queryParameter(key);
                rootMap.put(key, value);
            }
            String url = rootMap.size() == 0 ? httpUrl.url().toString() : httpUrl.url().toString().replace(httpUrl.query(), "");
            HttpUrl newHttpUrl = httpUrl;
            if (url.startsWith(RetrofitHelper.BASE_URL_M) && HttpManager.isLogin()) {

                newHttpUrl = httpUrl
                        .newBuilder()
                        .setEncodedQueryParameter("token", HttpManager.getToken())
                        .build();

            } else if (url.startsWith(RetrofitHelper.BASE_URL_BJ)) {

                if (HttpManager.isLogin()) {
                    newHttpUrl = httpUrl
                            .newBuilder()
                            .setEncodedQueryParameter("token", HttpManager.getToken())
                            .build();
                }

                if (HttpManager.isEncrypt()) {
                    Request newRequest = request.newBuilder()
                            .url(newHttpUrl).build();
                    Response response = chain.proceed(newRequest);
                    if (response.code() == 200) {
                        String respStr = response.body().string();
                        String body = URLDecoder.decode(ParamsEncrypt.decryptResponse(respStr), "utf-8");

                        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();
                    }
                }
            }

            Request newRequest = request.newBuilder()
                    .url(newHttpUrl).build();
            return chain.proceed(newRequest);

        } else if ("POST".equalsIgnoreCase(method)) {
            String url = httpUrl.url().toString();
            RequestBody requestBody = request.body();

            if (url.startsWith(RetrofitHelper.BASE_URL_M) && HttpManager.isLogin()) {
                //判断当前的请求 Body
                if (requestBody != null && requestBody instanceof FormBody) {
                    //创建一个新的FromBody
                    FormBody.Builder builder = new FormBody.Builder();
                    //获取原先的Body
                    FormBody body = (FormBody) requestBody;
                    //遍历Body
                    for (int i = 0; i < body.size(); i++) {
                        //取出原先Body的数据  存入新的Body里
                        builder.add(body.name(i), body.value(i));
                    }
                    //添加制定的公共参数到新的Body里  把原先的Body给替换掉
                    body = builder.add("token", HttpManager.getToken()).build();
                    //获取新的request  取代原先的request
                    request = request.newBuilder().post(body).build();
                }
            } else if (url.startsWith(RetrofitHelper.BASE_URL_BJ)) {

                //判断当前的请求 Body
                if (requestBody instanceof FormBody && HttpManager.isLogin()) {

                    //创建一个新的FromBody
                    FormBody.Builder builder = new FormBody.Builder()
                            .add("token", HttpManager.getToken());

                    //获取原先的Body
                    FormBody formBody = (FormBody) request.body();
                    //遍历Body
                    for (int i = 0; i < formBody.size(); i++) {
                        //取出原先Body的数据  存入新的Body里
                        builder.add(formBody.name(i), formBody.value(i));
                    }

                    //添加制定的公共参数到新的Body里  把原先的Body给替换掉
                    formBody = builder.build();
                    //获取新的request  取代原先的request
                    request = request.newBuilder().post(formBody).build();

                } else if (requestBody instanceof MultipartBody && HttpManager.isLogin()) {

                    MultipartBody multipartBody = (MultipartBody) requestBody;


                    MultipartBody.Builder builder = new MultipartBody.Builder();

                    for (MultipartBody.Part part : multipartBody.parts()) {
                        builder.addPart(part);
                    }
                    MultipartBody.Part part = MultipartBody.Part.createFormData("token", HttpManager.getToken());
                    builder.addPart(part);

                    request = request.newBuilder().post(builder.build()).build();

                } else if (requestBody.contentType() == null && HttpManager.isLogin()) {

                    //创建一个新的FromBody
                    FormBody.Builder builder = new FormBody.Builder()
                            .add("token", HttpManager.getToken());

                    request = request.newBuilder().post(builder.build()).build();
                }


                if (HttpManager.isEncrypt()) {
                    Response response = chain.proceed(request);
                    if (response.code() == 200) {
                        String respStr = response.body().string();
                        String body = URLDecoder.decode(ParamsEncrypt.decryptResponse(respStr), "utf-8");

                        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();

                    }
                }
            }

        } else if ("PUT".equalsIgnoreCase(method)) {
            //
        } else if ("DELETE".equalsIgnoreCase(method)) {

        }


        return chain.proceed(request);

    }


}
