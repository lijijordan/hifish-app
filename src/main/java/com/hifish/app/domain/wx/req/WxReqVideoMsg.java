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
 * @Description: 微信多媒体请求消息体
 */
public class WxReqVideoMsg extends WxReqBaseMsg {

	// 视频消息媒体 id，可以调用多媒体文件下载接口拉取数据
	private String MediaId;
	// 视频消息缩略图的媒体 id，可以调用多媒体文件下载接口拉取数据
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
