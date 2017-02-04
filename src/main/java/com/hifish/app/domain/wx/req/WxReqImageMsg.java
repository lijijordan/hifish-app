/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信请求图片信息体
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:35:55
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * The type Wx req image msg.
 *
 * @Description: 微信请求图片信息体
 */
public class WxReqImageMsg extends WxReqBaseMsg {

	// 图片链接
	private String PicUrl;

	/**
	 * Gets pic url.
	 *
	 * @return the pic url
	 */
	public String getPicUrl() {
		return PicUrl;
	}

	/**
	 * Sets pic url.
	 *
	 * @param picUrl the pic url
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
