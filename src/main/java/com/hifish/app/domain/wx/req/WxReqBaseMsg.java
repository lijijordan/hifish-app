/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信请求消息体基类
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:35:15
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * @Description: 微信请求消息基类
 */
public class WxReqBaseMsg {

	// 开发者微信号
	private String ToUserName;
	// 发送方帐号（一个 OpenID）
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/image/location/link/video/shortvideo）
	private String MsgType;
	// 消息 id，64 位整型
	private long MsgId;

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

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}
