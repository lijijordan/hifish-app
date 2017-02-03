/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信视屏实体
 * @author: yinhong  
 * @date: 2016年11月29日 下午2:18:04
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.msg;

/**
 * @Description: 微信视屏实体
 */
public class WxVideo {
	private String MediaId;
	private String Title;
	private String Description;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

}
