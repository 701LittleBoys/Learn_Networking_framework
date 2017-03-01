package com.imooc.download.file;

import android.content.Context;
import android.os.Environment;

import com.imooc.download.untils.MD5_Utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by 95262 on 2017/2/28.
 */

/*
* 对文件存储进行管理
*
* 将断点下载的文件，主动的控制管理于一个文件夹内
* */

public class FileStorageManager {

    private static final FileStorageManager sManager = new FileStorageManager();

    private Context mContext;

    public static FileStorageManager getInstance (){
        return sManager;
    }

    private FileStorageManager (){

    }

    public void init (Context context){
        this.mContext = context;
    }

    public File getFileByName (String url){

        //在此的时候，我们可以对url进行加密

        File parent = null;

        //判断SD卡是否进行了挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //挂在了SD卡，我们就让文件存贮在SD卡中
            parent = mContext.getExternalCacheDir();
        }
        else{
            //没有挂载SD卡，就让文件存贮在默认的文件目录中
            parent = mContext.getCacheDir();
        }

        String filename = MD5_Utils.generateCode(url);

        File file = new File(parent,filename);
        //判断文件是否存在，如果文件存在，我们就不用创建
        //如果文件不存在，我们将不再进行创建
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;

    }

}
