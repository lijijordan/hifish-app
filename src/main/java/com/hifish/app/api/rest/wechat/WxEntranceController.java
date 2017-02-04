/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信服务端入口
 * @author: yinhong
 * @date: 2016年11月25日 下午6:20:37
 * @version: V1.0
 */
package com.hifish.app.api.rest.wechat;

import com.hifish.app.entity.UserInfo;
import com.hifish.app.service.UserService;
import com.hifish.app.util.WxCommUtil;
import com.hifish.app.util.WxMsgType;
import com.hifish.app.util.WxMsgUtil;
import com.hifish.app.util.WxOAuthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Map;

/**
 * The type Wx entrance controller.
 *
 * @Description: 微信服务端入口
 */
@Api(value = "WxEntrance", description = "WxEntrance Controller")
@Controller
@RequestMapping("/wxentrance")
public class WxEntranceController {

    private static final Logger logger = LoggerFactory.getLogger(WxEntranceController.class);

    @Autowired
    private UserService userService;

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.appsecret}")
    private String appSecret;

    @Value("${ui.index.url}")
    private String indexUrl;

    @Autowired
    private WxCommUtil wxCommUtil;

    @Value("${ui.error.url}")
    private String errorUrl;

    @Autowired
    private WxEventDispatcher wxEventDispatcher;


    /**
     * Wx server verify.
     *
     * @param request   the request
     * @param response  the response
     * @param signature the signature
     * @param timestamp the timestamp
     * @param nonce     the nonce
     * @param echostr   the echostr
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @ApiOperation(value = "微信服务器验证")
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "getin", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public void wxServerVerify(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "signature", required = true) String signature,
                               @RequestParam(value = "timestamp", required = true) String timestamp,
                               @RequestParam(value = "nonce", required = true) String nonce,
                               @RequestParam(value = "echostr", required = true) String echostr)
            throws UnsupportedEncodingException {

        PrintWriter out = null;
        String result = "";
        try {
            out = response.getWriter();
            String[] str = {token, timestamp, nonce};
            Arrays.sort(str);
            String bigStr = str[0] + str[1] + str[2];
            String digest = DigestUtils.shaHex(bigStr.getBytes());
            // 确认请求来至微信服务器验证并验证成功
            if (digest.equals(signature)) {
                result = echostr;
            }
            out.print(result);
        } catch (IOException e) {
            logger.error("verify server failed.", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * Wx procedure.
     *
     * @param request  the request
     * @param response the response
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @ApiOperation(value = "执行微信公众号事件以及消息处理")
    @RequestMapping(value = "getin", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public void wxProcedure(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        PrintWriter out = null;
        String result = "";
        try {
            out = response.getWriter();
            // 解析微信请求的XML数据
            Map<String, String> map = WxMsgUtil.parseXml(request);
            String msgtype = map.get("MsgType");
            // 根据MsgType类型判断处理方式, 进入事件或消息处理
            if (WxMsgType.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
                result = wxEventDispatcher.processEvent(map);
            }
            out.print(result);
        } catch (IOException | DocumentException | URISyntaxException e) {
            logger.error("handle webchat message or event failed.", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * O auth 2.
     *
     * @param request  the request
     * @param response the response
     * @param code     the code
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @ApiOperation(value = "正式鉴权，页面鉴权以及获取用户openid,通过openid从数据库获取用户基本及信息")
    @RequestMapping(value = "oauth2.html", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public void oAuth2(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "code") String code)
            throws ServletException, IOException {

        String paramsUrl;

        if (!"authdeny".equals(code)) {
            try {
                // 获取网页授权access_token
                WxOAuthToken wxOAuth2Token = wxCommUtil.getOauth2Token(appId, appSecret, code);
                // 用户标识
                String openId = wxOAuth2Token.getOpenId();
                // 根据用户OpenID，从数据库获取用户和设备信息
                UserInfo userInfo = userService.getUserByAccount(openId);
//                UserDevInfo userDevInfo = userDevService.selectByUserAccount(openId);
                paramsUrl = "?account=" + userInfo.getOpenId() + "&nickname=" + userInfo.getNickName();
                // 鉴权完成，页面跳转到首页
                String path = request.getContextPath();

                response.sendRedirect(indexUrl + paramsUrl);
                // RequestDispatcher dispatcher =
                // request.getRequestDispatcher(PropertiesUtil.getProperty("ui.index.url") + paramsUrl);
                // dispatcher.forward(request, response);
                return;
            } catch (IOException | KeyManagementException | NoSuchAlgorithmException
                    | NoSuchProviderException e) {
                logger.error("oauth2 failed.", e);
            }
        }

        // 如果发生错误，跳转到错误界面
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(errorUrl);
        dispatcher.forward(request, response);
    }

    /**
     * Test.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @ApiOperation(value = "测试跳转接口")
    @RequestMapping(value = "test", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public void test(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("测试跳转!");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx690cea67271973b8&redirect_uri=http://0a76a94a.ngrok.io/wxentrance/oauth2.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        System.out.println(url);
        response.sendRedirect(url);
    }
}
