/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信事件派发器
 * @author: yinhong
 * @date: 2016年11月29日 下午1:44:33
 * @version: V1.0
 */
package com.hifish.app.api.rest.wechat;


import com.hifish.app.domain.wx.resp.WxRespTextMsg;
import com.hifish.app.entity.UserInfo;
import com.hifish.app.service.UserService;
import com.hifish.app.util.WxCommUtil;
import com.hifish.app.util.WxMsgType;
import com.hifish.app.util.WxMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

/**
 * The type Wx event dispatcher.
 *
 * @Description: 微信事件派发器 ，根据不同的事件类型进行处理
 */
@Component
public class WxEventDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(WxEventDispatcher.class);
    @Autowired
    private UserService userService;

    @Autowired
    private WxCommUtil wxCommUtil;


    /**
     * Process event string.
     *
     * @param map the map
     * @return the string
     * @throws URISyntaxException the uri syntax exception
     * @throws IOException        the io exception
     */
    public String processEvent(Map<String, String> map)
            throws URISyntaxException, IOException {

        String openId = map.get("FromUserName");
        String mpid = map.get("ToUserName");
        String eventType = map.get("Event");
        String eventKey = map.get("EventKey");
        String resultStr = "";
        switch (eventType) {
            // 关注事件
            case WxMsgType.EVENT_TYPE_SUBSCRIBE: {
                // 保存关注用户基本信息到数据库
                UserInfo userInfo = wxCommUtil.getUserInfoForWxClient(openId);
                userService.addUser(userInfo);
                resultStr = "关注空气质量,热爱生活!";
                break;
            }
            // 取消关注事件
            case WxMsgType.EVENT_TYPE_UNSUBSCRIBE: {
                // 删除用户
                userService.deleteUser(openId);
                break;
            }

            // 扫描二维码推送事件
            case WxMsgType.EVENT_TYPE_SCAN_WAITMSG: {
                break;
            }

            // 位置上报
            case WxMsgType.EVENT_TYPE_LOCATION: {
                break;
            }

            // 自定义菜单点击事件
            case WxMsgType.EVENT_TYPE_CLICK: {
                break;
            }

            // 自定义View事件
            case WxMsgType.EVENT_TYPE_VIEW: {
                break;
            }
            // 扫码设备二维码
            case WxMsgType.EVENT_TYPE_SCAN: {
                // 提示信息
                WxRespTextMsg txtMessage = new WxRespTextMsg();
                txtMessage.setToUserName(openId);
                txtMessage.setFromUserName(mpid);
                txtMessage.setCreateTime(new Date().getTime());
                txtMessage.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_TEXT);
                // 用户绑定设备
                if (!StringUtils.isEmpty(openId) && !StringUtils.isEmpty(eventKey)) {
                    userService.bindDevice(openId, eventKey);
                }
                txtMessage.setContent("绑定成功!");
                resultStr = WxMsgUtil.textMessageToXml(txtMessage);
            }
            default: {
                break;
            }
        }

        return resultStr;
    }

}
