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

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

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

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}
}
