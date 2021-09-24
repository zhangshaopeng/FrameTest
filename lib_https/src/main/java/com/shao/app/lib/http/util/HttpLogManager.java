package com.shao.app.lib.http.util;

import com.shao.app.lib.http.manager.HttpManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2016/6/11
 */

public class HttpLogManager {

    private static final String fileName = "request_log.log";

    /**
     * 写http日志
     *
     * @param log
     */
    public static synchronized void writeRequestLog(String log) {

        String path = HttpManager.context.getFilesDir().getAbsolutePath();
        File file = new File(path, fileName);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
