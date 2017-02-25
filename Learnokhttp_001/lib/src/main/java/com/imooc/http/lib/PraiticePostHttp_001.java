package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/25.
 */

public class PraiticePostHttp_001 {
    public static void main (String args[]){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("userName","ÁÖ÷ë")
                .add("userPassword","wojiaolinda")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/LearnOkHttpServer/HelloServlet")
                .post(formBody)
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
