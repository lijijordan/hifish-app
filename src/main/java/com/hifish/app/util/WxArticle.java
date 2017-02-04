/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信图文实体
 * @author: yinhong  
 * @date: 2016年11月29日 下午2:15:11
 * @version: V1.0  
 */
package com.hifish.app.util;

/**
 * The type Wx article.
 *
 * @Description: 微信图文实体
 */
public class WxArticle {

	// 图文消息名称
	private String Title;
	// 图文消息描述
	private String Description;
	// 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，小图 80*80，
	private String PicUrl;
	// 点击图文消息跳转链接
	private String Url;

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
		return null == Description ? "" : Description;
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
	 * Gets pic url.
	 *
	 * @return the pic url
	 */
	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}

	/**
	 * Sets pic url.
	 *
	 * @param picUrl the pic url
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	/**
	 * Gets url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return null == Url ? "" : Url;
	}

	/**
	 * Sets url.
	 *
	 * @param url the url
	 */
	public void setUrl(String url) {
		Url = url;
	}

}
