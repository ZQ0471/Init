package com.baimi.init.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @author zhang
 * @since 2024/8/1 下午5:50
 */
public class Md5Util {

    private static final String salt = "b4i[+45i+ds";
    public static String getMd5(String str) {
        return salt + DigestUtils.md5DigestAsHex(str.getBytes()).substring(1, 18);
    }
    public static String getCalcArgsMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
