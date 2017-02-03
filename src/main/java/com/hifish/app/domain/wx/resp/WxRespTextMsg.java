/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信文本信息回复消息体
 * @author: yinhong  
 * @date: 2016年11月29日 下午2:14:22
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.resp;

/**
 * @Description: 微信文本信息回复消息体
 */
public class WxRespTextMsg extends WxRespBaseMsg {

	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}