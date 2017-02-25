package com.imooc.http.lib;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/25.
 */

public class MultipartHttp {

    public static void main (String args[]){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"),new File("G://ͼƬ�ղ�/su27.jpg"));
        //�˴���MulTipartBody������Multipart���ļ������ʽ
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("fileName","su27.jpg",requestBody)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/LearnOkHttpServer/UploadServlet")
                .post(multipartBody)
                .build();
        try {
            Response response = client.newCall(request)
                    .execute();
            if (response.isSuccessful()){
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
