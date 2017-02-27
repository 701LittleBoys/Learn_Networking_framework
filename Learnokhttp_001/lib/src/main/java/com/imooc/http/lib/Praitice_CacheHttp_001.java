package com.imooc.http.lib;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/27.
 */

public class Praitice_CacheHttp_001 {

    //注意，我们测试缓存的时候：请求的网站一定支持缓存
    //Cache-Control:no-cache 就是不可以支持缓存

    public static void main (String arts[]) {



        int MaxCacheSize = 10*1024*1024;
        //这里，我们申请10MB的最大缓存资源

        //此处，我们申请一个Cache类
        //此类属于OkHttp的框架，具体属性查看API文档
        Cache mycache = new Cache(new File("C:/Users/95262/Desktop/OkHttpServer_DownLoad_position")
                ,MaxCacheSize);

        //
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(mycache)
                .build();

        CacheControl mycachecontrol = new CacheControl.Builder()
                //.noCache()
                //指示最大的缓存有效期的函数
                //此处的语意：强制使用缓存，缓存的有效期为365天
                //TimeUnit.xxx : 设置缓存使用的单位，有DAY,HOURS等等
                //永远不进行requset请求
                .maxStale(365, TimeUnit.HOURS)
                .build();

        Request request = new Request.Builder()
                .url("http://www.qq.com")
                .cacheControl(mycachecontrol)
                .build();

        try {

            System.out.println("===========这是第一次请求的结果==========");
            Response response = client.newCall(request)
                    .execute();

            String body = response.body().string();
            //此处需要注意：
            //如果response是正常的网络请求，那么我们的response不为空

            System.out.println("我们第一次申请的network response:"
             + response.networkResponse());
            System.out.println("我们第一次申请的cache response:"
             + response.cacheResponse());

            System.out.println("===========这是第二次请求的结果==========");
            Response response2 = client.newCall(request)
                    .execute();

            String body2 = response2.body().string();

            System.out.println("我们第二次申请的network response:"
             + response2.networkResponse());
            System.out.println("我们第二次申请的cache response:"
             + response2.cacheResponse());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
