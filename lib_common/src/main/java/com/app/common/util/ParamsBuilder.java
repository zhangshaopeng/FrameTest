package com.com.app.common.util;

import com.app.common.util.RSAUtil;
import com.shao.app.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * p>Describe:
 * p>Company:Dyne
 * p>@Author:zsp
 * p>Data:2019/2/12.
 */
public class ParamsBuilder {
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

    public ParamsBuilder putEncryptMD5(String key, String value) {
        map.put(key, MD5Utils.getMD5(value));
        return this;
    }

    public ParamsBuilder putEncryptRSA(String key, String value) {
        map.put(key, RSAUtil.encryptDataByPublicKey(value));
        return this;
    }

    public ParamsBuilder put(String key, Object value) {
        map.put(key, value);
        return this;
    }


    /**
     *  接口调用此方法得到最终请求参数
     *
     * @return
     */
    public Map<String, Object> build() {
        return map;
    }

}
