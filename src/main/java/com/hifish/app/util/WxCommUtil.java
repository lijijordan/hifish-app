/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信公共工具类
 * @author: yinhong
 * @date: 2016年12月1日 上午9:01:41
 * @version: V1.0
 */
package com.hifish.app.util;

import com.hifish.app.entity.UserInfo;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @Description: 微信公共工具类
 */
@Service
public class WxCommUtil {

    private static final Logger logger = LoggerFactory.getLogger(WxCommUtil.class);

    @Value("${wechat.cgi.userinfo.url}")
    private String wechaturl;

    @Value("jsapi_ticket")
    private String jsapiTicket;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.oauth_token.url}")
    private String authTokenUrl;

    @Value("${wechat.oauth_token.type}")
    private String oauthTokenType;

    @Value("${wechat.sns.userinfo.url}")
    private String snsUserInfoUrl;

    /**
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @Description: 微信客户端根据用户openid获取用户基本信息
     * @return: WxUserInfo
     */
    public UserInfo getUserInfoForWxClient(String openid)
            throws IOException {

        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", WechatTokenHelper.getToken());
        params.put("openid", openid);
        params.put("lang", "zh_CN");

        String subscribers =
                null;
        try {
            subscribers = HttpReqestUtil.sendGet(wechaturl, params);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            logger.info("获取用户信息失败");
        }

        logger.info("获取用户信息,返回JSON:{}", subscribers);
        UserInfo wxUserInfo = new UserInfo();
        if (!StringUtils.isEmpty(subscribers) && subscribers.indexOf("errcode") <= 0) {
            JSONObject jsonObject = new JSONObject(subscribers);
            wxUserInfo.setNickName(jsonObject.getString("nickname"));
            wxUserInfo.setSex(jsonObject.getString("sex"));
            wxUserInfo.setCity(jsonObject.getString("city"));
            wxUserInfo.setProvince(jsonObject.getString("province"));
            wxUserInfo.setCountry(jsonObject.getString("country"));
            wxUserInfo.setOpenId(jsonObject.getString("openid"));
            wxUserInfo.setHeadImageUrl(jsonObject.getString("headimgurl"));
            wxUserInfo.setSubscribe(jsonObject.getString("subscribe"));
            wxUserInfo.setLanguage(jsonObject.getString("language"));
            wxUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
            wxUserInfo.setRemark(jsonObject.getString("remark"));
            wxUserInfo.setGroupId(jsonObject.getString("groupid"));
        } else {
            wxUserInfo.setOpenId(openid);
            logger.info("get user info failed by user {}.", openid);
        }
        return wxUserInfo;
    }

    /**
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @Description: 获取前端jssdk页面配置需要用到的配置参数
     * @return: HashMap<String,String>
     */
    @SuppressWarnings("deprecation")
    public HashMap<String, String> getJsSDKParams(String url)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String nonce_str = create_nonce_str();
        String timestamp = createTimeStamp();
        String jsapi_ticket = jsapiTicket;
        // 注意这里参数名必须全部小写，且必须有序
        String params = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp="
                + timestamp + "&url=" + url;

        MessageDigest crypt = MessageDigest.getInstance(EncryptType.SHA_1);
        crypt.reset();
        crypt.update(params.getBytes(HTTP.UTF_8));
        String signature = byteToHex(crypt.digest());
        HashMap<String, String> returnParams = new HashMap<String, String>();
        returnParams.put("appId", appid);
        returnParams.put("timestamp", timestamp);
        returnParams.put("nonceStr", nonce_str);
        returnParams.put("signature", signature);

        return returnParams;
    }

    /**
     * @Description: hash数组补齐为16进制后以字符串形式输出
     * @return: String
     */
    public String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * @Description: 创建当前时间时间戳
     * @return: String
     */
    public String createTimeStamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @Description: 获取网页授权凭证
     * @return: WxOAuthToken
     */
    public WxOAuthToken getOauth2Token(String appId, String appSecret, String code)
            throws IOException, KeyManagementException, NoSuchAlgorithmException,
            NoSuchProviderException {

        String oauthTokenUrl = authTokenUrl;
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", oauthTokenType);

        String oauthTokenUrlStr = HttpReqestUtil.buildURL(oauthTokenUrl, params);
        // 获取网页授权凭证
        JSONObject jsonObject =
                HttpReqestUtil.httpsRequest(oauthTokenUrlStr, HttpReqestUtil.HTTP_REQUESTMETHOD_GET, null);

        WxOAuthToken wxOauth2Token = null;
        if (null != jsonObject) {
            wxOauth2Token = new WxOAuthToken();
            wxOauth2Token.setAccessToken(jsonObject.getString("access_token"));
            wxOauth2Token.setExpiresIn(jsonObject.getInt("expires_in"));
            wxOauth2Token.setRefreshToken(jsonObject.getString("refresh_token"));
            wxOauth2Token.setOpenId(jsonObject.getString("openid"));
            wxOauth2Token.setScope(jsonObject.getString("scope"));
        }

        return wxOauth2Token;
    }

    /**
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @Description: 通过网页授权获取用户信息
     * @return: WxUser
     */
    public UserInfo getUserInfobyOAuth2(String accessSnsToken, String openId)
            throws IOException, KeyManagementException, NoSuchAlgorithmException,
            NoSuchProviderException {

        String userInfoUrl = snsUserInfoUrl;
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessSnsToken);
        params.put("openid", openId);
        String userInfoUrlFinal = HttpReqestUtil.buildURL(userInfoUrl, params);

        // 通过网页授权获取用户信息
        JSONObject jsonObject =
                HttpReqestUtil.httpsRequest(userInfoUrlFinal, HttpReqestUtil.HTTP_REQUESTMETHOD_GET, null);

        UserInfo wcu = null;
        if (null != jsonObject) {
            wcu = new UserInfo();
            wcu.setOpenId(jsonObject.getString("openid"));
            wcu.setNickName(jsonObject.getString("nickname"));
            // 性别（1是男性，2是女性，0是未知）
            wcu.setSex(jsonObject.getString("sex"));
            wcu.setCountry(jsonObject.getString("country"));
            wcu.setProvince(jsonObject.getString("province"));
            wcu.setCity(jsonObject.getString("city"));
            wcu.setHeadImageUrl(jsonObject.getString("headimgurl"));
        }

        return wcu;
    }

    private String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

}