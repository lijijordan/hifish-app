/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信返回消息体基类
 * @author: yinhong
 * @date: 2016年11月29日 下午2:07:28
 * @version: V1.0
 */
package com.hifish.app.domain.wx.resp;

/**
 * @Description: 微信返回消息体基类
 */
public class WxRespBaseMsg {

  // 接收方帐号（收到的 OpenID）
  private String ToUserName;
  // 开发者微信号
  private String FromUserName;
  // 消息创建时间 （整型）
  private long CreateTime;
  // 消息类型（text/music/news）
  private String MsgType;

  public String getToUserName() {
    return ToUserName;
  }

  public void setToUserName(String toUserName) {
    ToUserName = toUserName;
  }

  public String getFromUserName() {
    return FromUserName;
  }

  public void setFromUserName(String fromUserName) {
    FromUserName = fromUserName;
  }

  public long getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(long createTime) {
    CreateTime = createTime;
  }

  public String getMsgType() {
    return MsgType;
  }

  public void setMsgType(String msgType) {
    MsgType = msgType;
  }
}
