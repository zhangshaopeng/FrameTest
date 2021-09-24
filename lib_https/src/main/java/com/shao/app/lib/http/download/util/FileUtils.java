package com.shao.app.lib.http.download.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Description:文件管理
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/4/19
 */
public class FileUtils {
    /**
     * @param dirPath     文件夹路径
     * @param fileName    文件名
     * @param inputStream 输入流
     */
    public static void saveFile(String dirPath, String fileName, InputStream inputStream) throws IOException {

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }

        byte[] buf = new byte[3072];
        int len;
        FileOutputStream fos = new FileOutputStream(file);
        while ((len = inputStream.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        inputStream.close();
        fos.flush();

    }
    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String filePath = null;
        if (scheme == null)
            filePath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            filePath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {

            String[] filePathColumn = {MediaStore.MediaColumns.DATA};
            ContentResolver contentResolver = context.getContentResolver();

            Cursor cursor = contentResolver.query(uri, filePathColumn, null,null, null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return filePath;
    }

    public static void createDir(String dir){
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
