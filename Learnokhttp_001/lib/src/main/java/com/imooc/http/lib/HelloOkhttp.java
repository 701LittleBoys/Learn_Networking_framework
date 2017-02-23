package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/22.
 */

public class HelloOkhttp {
    public static void main (String args[]){
        OkHttpClient client = new OkHttpClient();
        //此处是一种设计模式，叫做构建者模式
        Request request = new Request.Builder().url("http://www.baidu.com")
                .build();//创建一个Request对象
        try {
            Response response = client.newCall(request).execute();
            //对response对象进行请求判断，如果判断请求成功，那么就可以获得response对象
            if(response.isSuccessful()){
                //System.out.println(response.body().toString());
                System.out.println("请求的结果：");
                System.out.println(response.body().string());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
