package com.shao.app.lib.http.result;


/**
 * Description:接受结果实体bean
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/3/12
 */

public class HttpRespResult<T> {

    private static final int SUCCESS_CODE = 200;

    private String message;
    private Integer code;
    private T data;

    public boolean isSuccess() {
        return code != null && code == SUCCESS_CODE;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpResponseResult{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
