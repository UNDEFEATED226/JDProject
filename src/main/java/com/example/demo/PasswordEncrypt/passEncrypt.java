package com.example.demo.PasswordEncrypt;

public class passEncrypt {

    private static final String slat = "&%bder***&&%%$$#@";

    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}

