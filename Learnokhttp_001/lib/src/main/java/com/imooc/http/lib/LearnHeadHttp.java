package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/23.
 */

public class LearnHeadHttp {

    public static void main(String args[]){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.imooc.com")
                .addHeader("User-Agent","from nate http").build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                Headers my_res_header = response.headers();
                for (int i = 0;i < my_res_header.size();i++){
                    System.out.println(my_res_header.name(i).toString() + " : " + my_res_header.value(i).toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
