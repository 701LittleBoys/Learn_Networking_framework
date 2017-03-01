package com.imooc.download.untils;

/**
 * Created by 95262 on 2017/2/28.
 */

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对url进行MD5加密算法
 */

public class MD5_Utils {

    public static  String generateCode (String url){
        if (TextUtils.isEmpty(url)){
            return null;
        }
        else{
            /**
             * 此处有毒：
             *      使用了Android内置打的加密工具,
             *      利用反射的原理，通过反射来传递参数，我们的参数
             *      是：加密算法的名称
             */
            StringBuffer buffer = new StringBuffer();
            try {
                MessageDigest digest = MessageDigest.getInstance("md5");

                //传递的参数是Bytes[]数组，可以将url转变成bytes[]
                digest.update(url.getBytes());

                //获取经过加密处理的数据
                byte[] cipher = digest.digest();

                //此时加密的数据是二进制流，我们没有办法查看，可以转换成十六进制
                /**
                 * 此时文件的处理比较麻烦，还需要回头查看
                 */

                for (byte b : cipher) {
                    String hexStr =Integer.toHexString(b&0xff);
                    buffer.append(hexStr.length() == 1 ? "0" + hexStr:hexStr);

                }



            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            return buffer.toString();

        }
    }

}
