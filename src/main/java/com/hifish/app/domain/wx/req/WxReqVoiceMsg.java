/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信语音请求消息体
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:38:49
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * The type Wx req voice msg.
 *
 * @Description: 微信语音请求消息体
 */
public class WxReqVoiceMsg extends WxReqBaseMsg {

	// 媒体 ID
	private String MediaId;
	// 语音格式
	private String Format;

	/**
	 * Gets media id.
	 *
	 * @return the media id
	 */
	public String getMediaId() {
		return MediaId;
	}

	/**
	 * Sets media id.
	 *
	 * @param mediaId the media id
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	/**
	 * Gets format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return Format;
	}

	/**
	 * Sets format.
	 *
	 * @param format the format
	 */
	public void setFormat(String format) {
		Format = format;
	}
}
