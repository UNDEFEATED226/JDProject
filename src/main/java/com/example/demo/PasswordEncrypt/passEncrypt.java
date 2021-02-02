package com.example.demo.passwordencrypt;

public class PassEncrypt {

    private static final String slat = "&%bder***&&%%$$#@";

    /**
     * 生成加密密码
     * @param str 需加密的密码
     * @return 加密后的密码
     */
    public static String getMd5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}

