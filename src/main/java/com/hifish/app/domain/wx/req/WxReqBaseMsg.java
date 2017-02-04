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
 * The type Wx req base msg.
 *
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

	/**
	 * Gets to user name.
	 *
	 * @return the to user name
	 */
	public String getToUserName() {
		return ToUserName;
	}

	/**
	 * Sets to user name.
	 *
	 * @param toUserName the to user name
	 */
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	/**
	 * Gets from user name.
	 *
	 * @return the from user name
	 */
	public String getFromUserName() {
		return FromUserName;
	}

	/**
	 * Sets from user name.
	 *
	 * @param fromUserName the from user name
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	/**
	 * Gets create time.
	 *
	 * @return the create time
	 */
	public long getCreateTime() {
		return CreateTime;
	}

	/**
	 * Sets create time.
	 *
	 * @param createTime the create time
	 */
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	/**
	 * Gets msg type.
	 *
	 * @return the msg type
	 */
	public String getMsgType() {
		return MsgType;
	}

	/**
	 * Sets msg type.
	 *
	 * @param msgType the msg type
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	/**
	 * Gets msg id.
	 *
	 * @return the msg id
	 */
	public long getMsgId() {
		return MsgId;
	}

	/**
	 * Sets msg id.
	 *
	 * @param msgId the msg id
	 */
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}
