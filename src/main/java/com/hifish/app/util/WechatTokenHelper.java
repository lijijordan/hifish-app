package com.hifish.app.util;


public class WechatTokenHelper {


    public static final String APP_ID = "wx690cea67271973b8";
    public static final String APP_SECRET = "375173429b9f4a502dc849119c37460b";
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static String token = "";

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        WechatTokenHelper.token = token;
    }
}
