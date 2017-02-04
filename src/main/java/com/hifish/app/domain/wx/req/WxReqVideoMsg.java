/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信多媒体请求消息体
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:38:18
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * The type Wx req video msg.
 *
 * @Description: 微信多媒体请求消息体
 */
public class WxReqVideoMsg extends WxReqBaseMsg {

	// 视频消息媒体 id，可以调用多媒体文件下载接口拉取数据
	private String MediaId;
	// 视频消息缩略图的媒体 id，可以调用多媒体文件下载接口拉取数据
	private String ThumbMediaId;

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
	 * Gets thumb media id.
	 *
	 * @return the thumb media id
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	/**
	 * Sets thumb media id.
	 *
	 * @param thumbMediaId the thumb media id
	 */
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
