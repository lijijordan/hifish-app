/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信消息处理转发器
 * @author: yinhong
 * @date: 2016年11月29日 下午1:42:03
 * @version: V1.0
 */
package com.hifish.app.api.rest.wechat;

import com.hifish.app.util.WxMsgType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * The type Wx msg dispatcher.
 *
 * @Description: 微信消息处理转发器 ，根据不同类型消息进行不同处理
 */
@Component
public class WxMsgDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(WxMsgDispatcher.class);

    /**
     * Process message string.
     *
     * @param map the map
     * @return the string
     */
    public String processMessage(Map<String, String> map) {

        String openid = map.get("FromUserName");
        String mpid = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String result = "";

        switch (msgType) {
            // 文本消息
            case WxMsgType.REQ_MESSAGE_TYPE_TEXT: {
                // WxRespTextMsg txtmsg = new WxRespTextMsg();
                // txtmsg.setToUserName(openid);
                // txtmsg.setFromUserName(mpid);
                // txtmsg.setCreateTime(new Date().getTime());
                // txtmsg.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_TEXT);
                // txtmsg.setContent("你好，这里是Terry个人账号！");
                // result = WxMsgUtil.textMessageToXml(txtmsg);
                break;
            }

            // 图文消息
            case WxMsgType.REQ_MESSAGE_TYPE_IMAGE: {
                // WxRespNewsMsg newmsg = new WxRespNewsMsg();
                // newmsg.setToUserName(openid);
                // newmsg.setFromUserName(mpid);
                // newmsg.setCreateTime(new Date().getTime());
                // newmsg.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_NEWS);
                //
                // WxArticle article = new WxArticle();
                // article.setDescription("这是图文消息 ");
                // article.setPicUrl(
                // "http://p4.gexing.com/G1/M00/8D/97/rBACFFOmEOyRqIiyAAAhbwMbRv4671_200x200_3.jpg?recache=20131108");
                // article.setTitle("小美女");
                // article.setUrl("http://image.baidu.com");
                // List<WxArticle> list = new ArrayList<WxArticle>();
                // list.add(article);
                //
                // newmsg.setArticleCount(list.size());
                // newmsg.setArticles(list);
                // result = WxMsgUtil.newsMessageToXml(newmsg);
                break;
            }

            // 链接消息
            case WxMsgType.REQ_MESSAGE_TYPE_LINK: {
                break;
            }

            // 位置消息
            case WxMsgType.REQ_MESSAGE_TYPE_LOCATION: {
                break;
            }

            // 视屏消息
            case WxMsgType.REQ_MESSAGE_TYPE_VIDEO: {
                break;
            }

            // 语音消息
            case WxMsgType.REQ_MESSAGE_TYPE_VOICE: {
                break;
            }

            // 默认
            default: {
                // logger.info("no webchat event hanlder.");
                break;
            }

        }

        return result;
    }
}
