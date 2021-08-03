package com.blog.util;/**
 * @author Lu Jianqiang
 * @date 2021/7/26 20 09
 * discription
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MD5Utils
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/26 20:09
 * @Version 1.0
 */


public class MD5Utils {
    public static String code(String str){
        /**
         * @Description: MD5code
         * @Param: [str]
         * @return: password
         * @Author: Lu Jianqiang
         * @Date: 2021/7/26
         */
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] digest = md5.digest();
            int i;
            StringBuffer sb = new StringBuffer("");
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    sb.append("0");
                    sb.append(Integer.toHexString(i));
            }
            return sb.toString();//32bit
//              return sb.toString().substring(8,24);//16bit
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
//test
    public static void main(String[] args) {
        System.out.println(MD5Utils.code("123456"));
    }
}
