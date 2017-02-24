package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/24.
 */

public class PraiticeQueryHttp_001 {

    public static void main (String args[]){
        OkHttpClient client = new OkHttpClient();
        //采用的API是：和风天气接口
        HttpUrl httpUrl = HttpUrl.parse("https://free-api.heweather.com/v5/weather").newBuilder()
                .addQueryParameter("city","shanghai")
                .addQueryParameter("key","8cb9b50caff345e4ab2ea97468dd0b06")
                .build();
        Request request = new Request.Builder().url(httpUrl.toString()).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                System.out.println("请求的HttpUrl对象的数据显示 :" + httpUrl.toString());
                System.out.println("Response对象获取的数据 ： " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
