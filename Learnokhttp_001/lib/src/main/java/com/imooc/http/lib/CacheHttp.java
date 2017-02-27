package com.imooc.http.lib;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/25.
 */

public class CacheHttp {
    public static void main (String args[]){

        int maxCacheSize = 10 * 1024 * 1024;
        Cache cache =
                new Cache(new File("C:/Users/95262/Desktop/OkHttpServer_DownLoad_position"),
                        maxCacheSize);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .cache(cache)
                .build();

        System.out.println("运行到请求Resquest的服务");
        Request request = new Request.Builder()
                .url("http://www.qq.com")
                .build();
        try {
            Response response = client.newCall(request)
                    .execute();
            String body = response.body().string();
            //如果是新服务器请求得到的response，那么不为空
            System.out.println("network response " + response.networkResponse());
            //如果是从缓存中取出的，那么不为空
            System.out.println("cache response " + response.cacheResponse());
            System.out.println("==========这是美丽的分割线=======");


            Response response1 = client.newCall(request)
                    .execute();
            String body1 = response1.body().string();
            System.out.println("network response " + response1.networkResponse());
            //如果是从缓存中取出的，那么不为空
            System.out.println("cache response " + response1.cacheResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
