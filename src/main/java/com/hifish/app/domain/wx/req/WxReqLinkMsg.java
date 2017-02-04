/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: TODO
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:36:36
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * The type Wx req link msg.
 *
 * @Description: TODO
 */
public class WxReqLinkMsg extends WxReqBaseMsg {

	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
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
	 * Gets url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return Url;
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