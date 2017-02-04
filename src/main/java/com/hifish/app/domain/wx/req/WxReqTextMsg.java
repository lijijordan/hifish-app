/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信文本请求消息体
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:37:45
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * The type Wx req text msg.
 *
 * @Description: 微信文本请求消息体
 */
public class WxReqTextMsg extends WxReqBaseMsg {

	// 消息内容
	private String Content;

	/**
	 * Gets content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * Sets content.
	 *
	 * @param content the content
	 */
	public void setContent(String content) {
		Content = content;
	}
}
