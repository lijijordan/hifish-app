package com.hifish.app.util;


/**
 * The type Wechat token helper.
 */
public class WechatTokenHelper {


    /**
     * The constant APP_ID.
     */
    public static final String APP_ID = "wx690cea67271973b8";
    /**
     * The constant APP_SECRET.
     */
    public static final String APP_SECRET = "375173429b9f4a502dc849119c37460b";
    /**
     * The constant TOKEN_URL.
     */
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static String token = "";

    /**
     * Gets token.
     *
     * @return the token
     */
    public static String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public static void setToken(String token) {
        WechatTokenHelper.token = token;
    }
}
