package com.shao.app.lib.http.okhttp;

import com.shao.app.lib.http.manager.HttpManager;

import java.io.IOException;
import java.io.InputStream;

import okio.Buffer;


/**
 * Description:证书认证
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/4/1
 */
public class CertificateStreamFactory {



    public static InputStream[] trustedCertificatesInputStream(String[] str) {

        InputStream[] inputStream = new InputStream[str.length];
        for (int i = 0; i < str.length; i++) {
            inputStream[i] = new Buffer()
                    .writeUtf8(str[i])
                    .inputStream();
        }
        return inputStream;
    }


    public static InputStream[] trustedCertificatesInputStream(String assetFileName) {

        InputStream[] inputStream = new InputStream[1];
        try {
            inputStream[0] = HttpManager.context.getAssets().open(assetFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
