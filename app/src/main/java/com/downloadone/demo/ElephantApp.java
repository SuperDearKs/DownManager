package com.downloadone.demo;


import android.app.Application;


public class ElephantApp extends Application {
    private static ElephantApp instance;

    public static ElephantApp getInstance() {
        if (instance == null) {
            instance = new ElephantApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        initConfig();

    }

    void initConfig() {
        //初始化文件缓存路径
        String cache = StorageUtils.getCacheDirectory(this) + "/" + MD5Tools.hashKeyForDisk("com.downloadone.master");
        AppStoragePath.setCachePath(cache);
    }


}
