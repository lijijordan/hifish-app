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
 * @Description: 微信请求图片信息体
 */
public class WxReqImageMsg extends WxReqBaseMsg {

	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
