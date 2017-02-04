package com.hifish.app.util;


import com.hifish.app.task.WechatTask;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The type Menu main.
 */
public class MenuMain {


    /**
     * Gets home page url.
     *
     * @return the home page url
     */
    public static String getHomePageUrl() {
        String sampleUrl = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=wx690cea67271973b8" +
                "&redirect_uri=http%3A%2F%2Fa4bc785e.ngrok.io%2F" +
                "&response_type=code&scope=snsapi_base" +
                "&state=test" +
                "#wechat_redirect";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + WechatTokenHelper.APP_ID +
                "&redirect_uri=http%3A%2F%2F0a76a94a.ngrok.io%2Fwxentrance%2Ftest" +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE" +
                "#wechat_redirect";

        return url;
    }

    /**
     * Generate qr code.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        JSONObject menujson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject button1 = new JSONObject();
        button1.put("name", "空气质量");
        button1.put("type", "view");
        button1.put("url", getHomePageUrl());

        JSONObject button2 = new JSONObject();
        button2.put("name", "绑定设备");
        button2.put("type", "scancode_push");
        button2.put("key", "bindDevice");


        jsonArray.put(button1);
        jsonArray.put(button2);
        menujson.put("button", jsonArray);
        System.out.println(menujson);
        //这里为请求接口的 url   +号后面的是 token，这里就不做过多对 token 获取的方法解释
        String token = WechatTask.getToken();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        System.out.println(url);
        try {
            String rs = HttpUtils.sendPostBuffer(url, menujson.toString());
            System.out.println(rs);
        } catch (Exception e) {
            System.out.println("请求错误！");
        }
    }

}