package com.imooc.http.lib;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/23.
 */

public class ASyncHttp {

//    ���ַ�����һ��ͬ�����󣬻�������ǰ���߳�
//     һ�㶼���첽��������
    public static void sendRequest(String url){
        OkHttpClient client = new OkHttpClient();
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

    public static void sendAyncRequest (String url){
        System.out.println("��ʱ���з������߳�ID = " + Thread.currentThread().getId());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        //�˴���ͬ����ʱ�����һ���ص�����
        client.newCall(request).enqueue(new Callback() {
            @Override
            //����ʧ�ܵĻص�����
            public void onFailure(Call call, IOException e) {

            }
            //����ɹ��Ļص�����
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    System.out.println(response.body().string());
                    //�����жϵ�ʱ�����ǲ���һ���ж��߳�ID�ķ���
                    System.out.println("��ʱ����������߳�ID = " + Thread.currentThread().getId());
                }
            }
        });
    }

    public static void main (String args[]){
        System.out.println("��ʱ������������߳�ID = " + Thread.currentThread().getId());
        /*����ͬ������ķ���*/
        //sendRequest("http://www.baidu.com");
        /*�����첽����ķ���*/
        sendAyncRequest("http://www.baidu.com");
        sendAyncRequest("http://www.baidu.com");
    }
}
