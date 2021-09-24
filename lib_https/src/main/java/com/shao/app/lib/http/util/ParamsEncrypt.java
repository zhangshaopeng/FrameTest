package com.shao.app.lib.http.util;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/7/11
 */
public class ParamsEncrypt {

    public static String encryptRequest(String param) {

        try {
            byte[] b = DESUtil.encrypt(param, DESUtil.KEY);
            return Base64Util.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decryptResponse(String resp) {

        try {
            byte[] decode = Base64Util.decode(resp);
            return DESUtil.decrypt(decode, DESUtil.KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
