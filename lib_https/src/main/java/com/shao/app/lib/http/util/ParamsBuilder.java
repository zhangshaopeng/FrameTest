package com.shao.app.lib.http.util;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shao.app.lib.http.manager.HttpManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/11
 */
public class ParamsBuilder {
    private static Gson gson = new Gson();
    private Map<String, Object> map;

    public ParamsBuilder() {
        map = new HashMap<>();
    }


    public ParamsBuilder(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }


    public ParamsBuilder(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }


    public ParamsBuilder(Map<String, Object> m) {
        map = new HashMap<>(m);
    }


    public ParamsBuilder put(String key, Object value) {
        map.put(key, value);
        return this;
    }


    /**
     * bjopenapi.shao.com 接口调用此方法得到最终请求参数
     *
     * @return
     */
    public Map<String, Object> build() {
        String json = gson.toJson(map);
        Logger.e("origin requestBody:" + json);
        String md5Str = MD5Util.str2MD5(json);
        Map<String, Object> stringObjectMap = new ArrayMap<>();
        stringObjectMap.put("enc_data", HttpManager.isEncrypt() ? ParamsEncrypt.encryptRequest(json) : json);
        stringObjectMap.put("sign", TextUtils.isEmpty(md5Str) ? "" : md5Str);
        return stringObjectMap;
    }

    /**
     * bjopenapi.shao.com 接口在使用Multipart时
     * 调用此方法得到最终请求参数
     * <p>
     * https://blog.csdn.net/qq_33215972/article/details/68950838?locationNum=2&fps=1
     *
     * @return
     */
    public Map<String, RequestBody> buildMultipartBody() {

        Map<String, RequestBody> map = new HashMap<>();

        Map<String, Object> stringObjectMap = build();
        for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
            String key = stringObjectEntry.getKey();
            String value = (String) stringObjectEntry.getValue();

            map.put(key, RequestBody.create(null, String.valueOf(value)));
        }
        return map;
    }


    /**
     * https://m.wtoip.com 接口得到最终参数
     *
     * @return
     */
    public Map<String, Object> buildNoEncrypt() {
        return map;
    }

}
