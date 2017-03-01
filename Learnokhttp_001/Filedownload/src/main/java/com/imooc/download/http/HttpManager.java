package com.imooc.download.http;

import android.content.Context;

import com.imooc.download.file.FileStorageManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/3/1.
 *
 * 此处使用的设计模式：单例模式
 *
 */

public class HttpManager {

    private static final HttpManager httpManager = new HttpManager();
    //上下文变量
    private Context mContext;
    private OkHttpClient mClient;

    private HttpManager(){
        mClient = new OkHttpClient();
    }
    public static HttpManager getInstance (){
        return httpManager;
    }

    public void init (Context context){
        this.mContext = context;
    }

    /**
     * 同步请求
     * @param url
     * @return response
     */
    public Response syncRequest (String url){
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            return mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void a_syncRequest (final String url, final DownloadCallback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful() && callback != null){
                    callback.http_fail(1,"网络请求失败");
                }
                File file = FileStorageManager.getInstance()
                        .getFileByName(url);
                /**
                 * 此处是用作文件文件传输的时候的缓冲的作用
                 */
                byte[] buffer = new byte[1024 * 500];
                int len ;

                FileOutputStream fileOutputStream = new FileOutputStream(file);

                InputStream inputStream = response.body().byteStream();

                //我们要对文件进行传输写入的操作
                while ((len = inputStream.read(buffer,0,buffer.length)) != -1){
                    fileOutputStream.write(buffer,0,len);
                    fileOutputStream.flush();
                }

                callback.http_success(file);

            }
        });
    }
}
