package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/24.
 */

public class PostHttp {
    public static void main (String args[]){
        OkHttpClient client = new OkHttpClient();
        //RequestBody是一个接口

        FormBody body = new FormBody.Builder()
                .add("userName","林麟")
                .add("userPassword","123")
                .build();

        Request request = new Request.Builder().url("http://localhost:8080/LearnOkHttpServer/HelloServlet")
                .post(body)
                .build();
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
