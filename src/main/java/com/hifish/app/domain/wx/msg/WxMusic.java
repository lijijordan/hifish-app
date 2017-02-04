/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信音乐实体
 * @author: yinhong  
 * @date: 2016年11月29日 下午2:17:15
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.msg;

/**
 * The type Wx music.
 *
 * @Description: 微信音乐实体
 */
public class WxMusic {
	
	// 音乐名称
	private String Title;
	// 音乐描述
	private String Description;
	// 音乐链接
	private String MusicUrl;
	// 高质量音乐链接，WIFI 环境优先使用该链接播放音乐
	private String HQMusicUrl;

	private String ThumbMediaId; // 缩略图的媒体 id

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
	 * Gets music url.
	 *
	 * @return the music url
	 */
	public String getMusicUrl() {
		return MusicUrl;
	}

	/**
	 * Sets music url.
	 *
	 * @param musicUrl the music url
	 */
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	/**
	 * Gets hq music url.
	 *
	 * @return the hq music url
	 */
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	/**
	 * Sets hq music url.
	 *
	 * @param musicUrl the music url
	 */
	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}
}
