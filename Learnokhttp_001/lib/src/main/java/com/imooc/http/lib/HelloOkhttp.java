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
        //�˴���һ�����ģʽ������������ģʽ
        Request request = new Request.Builder().url("http://www.baidu.com")
                .build();//����һ��Request����
        try {
            Response response = client.newCall(request).execute();
            //��response������������жϣ�����ж�����ɹ�����ô�Ϳ��Ի��response����
            if(response.isSuccessful()){
                //System.out.println(response.body().toString());
                System.out.println("����Ľ����");
                System.out.println(response.body().string());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
