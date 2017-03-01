package com.imooc.download.http;

import java.io.File;

/**
 * Created by 95262 on 2017/3/1.
 *
 * 定义了一些网络回调的接口
 *
 * 注意 — 一些接口定义的原则：
 */

public interface DownloadCallback {
    //文件下载成功
    void http_success (File file);
    //文件下载失败
    void http_fail (int errorcode,String errorMessage);
    //显示下载的进度
    void http_progress (int progress);
}
