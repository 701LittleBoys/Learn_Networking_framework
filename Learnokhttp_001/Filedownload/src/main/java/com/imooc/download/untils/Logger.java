package com.imooc.download.untils;

import android.util.Log;

import java.util.Locale;

/**
 * Created by 95262 on 2017/2/28.
 */


/*
* 创建当前项目的管理日志类
*/
public class Logger {
    public  static  final  boolean DEBUG = true;

    public static void debug (String tag,String message,Object... args){
        if (DEBUG){
            Log.e(tag,String.format(Locale.getDefault(),message,args));
        }
    }

    public static void error (String tag, String message){
        if (DEBUG){
            Log.e(tag, message);
        }
    }

    public static void info (String tag ,String message){
        if (DEBUG){
            Log.i(tag, message);
        }
    }
}
