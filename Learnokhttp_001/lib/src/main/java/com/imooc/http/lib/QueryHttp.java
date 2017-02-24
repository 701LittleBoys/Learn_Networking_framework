package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/23.
 */

/*
    重新回顾知识点：
        get请求：参数依照字符串拼接为主，而且上传的数据量有大小的限制
        post请求：上传的数据量没有限制，对于重要的数据我们一般采用此方式，安全性较好
 */

public class QueryHttp {
    public static void main (String args[]){
        OkHttpClient client = new OkHttpClient();
        //addQueryParameter 的意思是：添加查询参数，可以无限制的追加你的参数
        //采用的API接口：和风天气
        HttpUrl httpUrl = HttpUrl.parse("https://free-api.heweather.com/v5/weather")
                .newBuilder()
                .addQueryParameter("city","beijing")
                .addQueryParameter("key","8cb9b50caff345e4ab2ea97468dd0b06")
                .build();
        System.out.println(httpUrl.toString());
        String url = httpUrl.toString();
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
