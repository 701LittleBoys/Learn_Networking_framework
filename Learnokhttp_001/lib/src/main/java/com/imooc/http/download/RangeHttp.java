package com.imooc.http.download;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 95262 on 2017/2/28.
 */

public class RangeHttp {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://mat1.gtimg.com/www/images/qq2012/guanjia2.png")
                .addHeader("Range","bytes=0-2")
                .build();

        /*
        *   �˴���Ҫע�⣺Range�ֶε���ʼ�ֶδ�0��ʼ����λbytes
        *
        *   �˴���Ҫע�⣺���������request�����м�����Range�ֶΣ�
        *   ��ô����������������е�response������Header(ͷ��)
        *   �м���һ������Content-Range����
        *
        *   Context_Range������˴�response���ĵ�Я�������ݴ�С�ķ�Χ
        *                   ����λbytes / �ļ����ܴ�С����λbytes
        */

        try {
            Response response = client.newCall(request).execute();

            System.out.println("content-length = " + response.body().contentLength());

            if (response.isSuccessful()){
                //�˴��õ�response���ĵ�����ͷ��
                Headers headers = response.headers();
                for (int i=0;i<headers.size();i++){
                    System.out.println(headers.name(i) + ":" + headers.value(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
