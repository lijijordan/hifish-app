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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 微信事件派发器，根据不同的事件类型进行处理
 */
@Component
public class WxEventDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(WxEventDispatcher.class);
    @Autowired
    private UserService userService;

    @Autowired
    private WxCommUtil wxCommUtil;


    public String processEvent(Map<String, String> map)
            throws URISyntaxException, IOException {

        String openid = map.get("FromUserName");
        String mpid = map.get("ToUserName");
        String eventType = map.get("Event");
        String resultStr = "";
        switch (eventType) {
            // 关注事件
            case WxMsgType.EVENT_TYPE_SUBSCRIBE: {
                // 保存关注用户基本信息到数据库
                UserInfo userInfo = wxCommUtil.getUserInfoForWxClient(openid);
                userService.addUser(userInfo);
                resultStr = "关注空气质量,热爱生活!";
                break;
            }

            // 取消关注事件
            case WxMsgType.EVENT_TYPE_UNSUBSCRIBE: {
                // 删除用户
                userService.deleteUser(openid);
                break;
            }

            // 扫描二维码推送事件
            case WxMsgType.EVENT_TYPE_SCAN_WAITMSG: {

//                // 提示信息
//                WxRespTextMsg txtmsg = new WxRespTextMsg();
//                txtmsg.setToUserName(openid);
//                txtmsg.setFromUserName(mpid);
//                txtmsg.setCreateTime(new Date().getTime());
//                txtmsg.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_TEXT);
//
//                // 设置用户与设备关系对象
//                UserDevInfo userDevInfo = new UserDevInfo();
//                userDevInfo.setDevSn(map.get("ScanResult"));
//                userDevInfo.setUserAccount(openid);
//
//                // 保存数据库
//                try {
//                    webchatEventDispatcher.userDevService.bindUserDev(userDevInfo);
//                    txtmsg.setContent(MessageUtil.getMessage("message.wx.devuserbind.success"));
//                } catch (BusinessException e) {
//                    logger.error("scan error.", e);
//                    txtmsg.setContent(MessageUtil.getMessage("message.wx.devuserbind.faild"));
//                }
//                resultStr = WxMsgUtil.textMessageToXml(txtmsg);

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
//                String sn = map.get("EventKey");
//                String appid = map.get("FromUserName");
//                logger.info("sn:{}, appid:{}", sn, appid);
//                WexinUtils.sendTextMessage("绑定设备成功!", appid);
                // 提示信息
                WxRespTextMsg txtmsg = new WxRespTextMsg();
                txtmsg.setToUserName(openid);
                txtmsg.setFromUserName(mpid);
                txtmsg.setCreateTime(new Date().getTime());
                txtmsg.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_TEXT);

                // 设置用户与设备关系对象
                UserInfo userDevInfo = new UserInfo();
                // 保存数据库
//                userService.bindUserDev(userDevInfo);
                txtmsg.setContent("绑定成功!");
                resultStr = WxMsgUtil.textMessageToXml(txtmsg);
            }
            default: {
                break;
            }
        }

        return resultStr;
    }

}
