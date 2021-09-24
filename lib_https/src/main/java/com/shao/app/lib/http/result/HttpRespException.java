package com.shao.app.lib.http.result;


/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/3/12
 */

public class HttpRespException extends RuntimeException {

    private int status;
    private int code;

    public HttpRespException(String message, int status, int code) {
        super(message);
        this.status = status;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
