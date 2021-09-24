package com.shao.app.lib.http.download.listener;

import java.io.File;

/**

/**
 * Description:下载方法
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/4/3
 */
public interface DownloadListener {

    void update(long bytesRead, long contentLength);

    void onSuccess(File file);

    void onFailure(Throwable t);
}
