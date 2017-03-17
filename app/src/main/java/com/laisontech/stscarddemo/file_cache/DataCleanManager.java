package com.laisontech.stscarddemo.file_cache;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by 80926 on 2016/12/20.
 */

public class DataCleanManager {
    /**
     *  清除应用的内部缓存
     * @param context
     */
    public static void cleanInternalCache(Context context){
        deleteFilesByDirectory(context.getCacheDir());
    }
    //清除本应用的数据库
    public static void cleanDatabase(Context context){
        deleteFilesByDirectory(new File("/data/data/"+context.getPackageName()+"/databases"));
    }
    public static void cleanDatabaseByName(Context context,String dbName){
        context.deleteDatabase(dbName);
    }
    //清除应用的sp
    public static void cleanSharedPreference(Context context){
        deleteFilesByDirectory(new File("/data/data/"+context.getPackageName()+"/shared_prefs"));
    }
   //清除/data/data/com.xxx.xxx/files下的内容 * *
    public static void cleanFiles(Context context){
        deleteFilesByDirectory(context.getFilesDir());
    }
    //清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
    public static void cleanExternalCache(Context context){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }
    //清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
    public static void cleanCustomCache(String filePath){
        deleteFilesByDirectory(new File(filePath));
    }
    //清除所有的数据
    public static void cleanAllData(Context context,String... filePaths){
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabase(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        if (filePaths==null){
            return;
        }
        for(String filePath:filePaths){
            cleanCustomCache(filePath);
        }
    }
    // 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
    public static void deleteFilesByDirectory(File directory){
        if (directory!=null&&directory.exists()&&directory.isDirectory()){
            for(File file : directory.listFiles()){
                file.delete();
            }
        }
    }
    //获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file){
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
    //删除指定目录下的文件
    public static void deleteFolderFile(String filePath,boolean deleteThisPath){
        if (!TextUtils.isEmpty(filePath)){
            try {
                File file = new File(filePath);
                if (file.isDirectory()){
                    File[] files = file.listFiles();
                    for(int i=0;i<files.length;i++){
                        deleteFolderFile(files[i].getAbsolutePath(),true);
                    }
                }
                if (deleteThisPath){
                    if (!file.isDirectory()){
                        file.delete();
                    }else {
                        if (file.listFiles().length==0){
                            file.delete();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //格式化
    public static String getFormatSize(double size){
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "BYTE";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
    public static String getCacheSize(File file){
        return getFormatSize(getFolderSize(file));
    }
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
