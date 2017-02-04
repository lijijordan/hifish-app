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
 * The type Wx video.
 *
 * @Description: 微信视屏实体
 */
public class WxVideo {
	private String MediaId;
	private String Title;
	private String Description;

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * Sets title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		Description = description;
	}

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

}
