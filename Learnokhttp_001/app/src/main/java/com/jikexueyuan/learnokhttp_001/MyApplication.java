package com.jikexueyuan.learnokhttp_001;

import android.app.Application;

import com.imooc.download.file.FileStorageManager;
import com.imooc.download.http.HttpManager;

/**
 * Created by 95262 on 2017/3/1.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FileStorageManager.getInstance().init(this);

        HttpManager.getInstance().init(this);




    }
}
