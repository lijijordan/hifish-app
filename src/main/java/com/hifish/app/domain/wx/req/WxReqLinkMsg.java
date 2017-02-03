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
 * @Description: TODO
 */
public class WxReqLinkMsg extends WxReqBaseMsg {

	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;

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

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}