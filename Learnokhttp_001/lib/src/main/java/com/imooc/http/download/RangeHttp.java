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
        *   此处需要注意：Range字段的起始字段从0开始，单位bytes
        *
        *   此处需要注意：如果我们在request报文中加入了Range字段，
        *   那么，服务器到浏览器中的response报文在Header(头部)
        *   中加入一个属性Content-Range属性
        *
        *   Context_Range：代表此次response报文的携带的数据大小的范围
        *                   ，单位bytes / 文件的总大小，单位bytes
        */

        try {
            Response response = client.newCall(request).execute();

            System.out.println("content-length = " + response.body().contentLength());

            if (response.isSuccessful()){
                //此处拿到response报文的所有头部
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
