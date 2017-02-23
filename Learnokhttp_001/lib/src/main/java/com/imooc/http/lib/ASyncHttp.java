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

//    这种方法是一种同步请求，会阻塞当前的线程
//     一般都是异步请求数据
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
        System.out.println("此时运行方法的线程ID = " + Thread.currentThread().getId());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        //此处不同，此时会进行一个回调操作
        client.newCall(request).enqueue(new Callback() {
            @Override
            //请求失败的回调方法
            public void onFailure(Call call, IOException e) {

            }
            //请求成功的回调方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    System.out.println(response.body().string());
                    //进行判断的时候，我们采用一种判断线程ID的方法
                    System.out.println("此时申请请求的线程ID = " + Thread.currentThread().getId());
                }
            }
        });
    }

    public static void main (String args[]){
        System.out.println("此时运行主程序的线程ID = " + Thread.currentThread().getId());
        /*进行同步请求的方法*/
        //sendRequest("http://www.baidu.com");
        /*进行异步请求的方法*/
        sendAyncRequest("http://www.baidu.com");
        sendAyncRequest("http://www.baidu.com");
    }
}
