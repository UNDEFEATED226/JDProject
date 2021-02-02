package com.jd.iot.admin.passwordencrypt;

import java.io.UnsupportedEncodingException;

public class PassEncrypt {

    private static final String slat = "&%bder***&&%%$$#@";

    /**
     * 生成加密密码
     * @param str 需加密的密码
     * @return 加密后的密码
     */
    public static String getMd5(String str) {
        String base = str + "/" + slat;
        String md5="";
        try {
            md5 = DigestUtils.md5DigestAsHex(base.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return md5;
    }
}

