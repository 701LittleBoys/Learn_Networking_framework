package com.imooc.http.lib;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/27.
 */

public class Praitice_CacheHttp_001 {

    //ע�⣬���ǲ��Ի����ʱ���������վһ��֧�ֻ���
    //Cache-Control:no-cache ���ǲ�����֧�ֻ���

    public static void main (String arts[]) {



        int MaxCacheSize = 10*1024*1024;
        //�����������10MB����󻺴���Դ

        //�˴�����������һ��Cache��
        //��������OkHttp�Ŀ�ܣ��������Բ鿴API�ĵ�
        Cache mycache = new Cache(new File("C:/Users/95262/Desktop/OkHttpServer_DownLoad_position")
                ,MaxCacheSize);

        //
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(mycache)
                .build();

        CacheControl mycachecontrol = new CacheControl.Builder()
                //.noCache()
                //ָʾ���Ļ�����Ч�ڵĺ���
                //�˴������⣺ǿ��ʹ�û��棬�������Ч��Ϊ365��
                //TimeUnit.xxx : ���û���ʹ�õĵ�λ����DAY,HOURS�ȵ�
                //��Զ������requset����
                .maxStale(365, TimeUnit.HOURS)
                .build();

        Request request = new Request.Builder()
                .url("http://www.qq.com")
                .cacheControl(mycachecontrol)
                .build();

        try {

            System.out.println("===========���ǵ�һ������Ľ��==========");
            Response response = client.newCall(request)
                    .execute();

            String body = response.body().string();
            //�˴���Ҫע�⣺
            //���response������������������ô���ǵ�response��Ϊ��

            System.out.println("���ǵ�һ�������network response:"
             + response.networkResponse());
            System.out.println("���ǵ�һ�������cache response:"
             + response.cacheResponse());

            System.out.println("===========���ǵڶ�������Ľ��==========");
            Response response2 = client.newCall(request)
                    .execute();

            String body2 = response2.body().string();

            System.out.println("���ǵڶ��������network response:"
             + response2.networkResponse());
            System.out.println("���ǵڶ��������cache response:"
             + response2.cacheResponse());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
