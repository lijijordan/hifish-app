/**  
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信地理位置请求消息体
 * @author: yinhong  
 * @date: 2016年11月29日 下午1:37:12
 * @version: V1.0  
 */
package com.hifish.app.domain.wx.req;

/**
 * The type Wx req location msg.
 *
 * @Description: 微信地理位置请求消息体
 */
public class WxReqLocationMsg extends WxReqBaseMsg {

	// 地理位置维度
	private String Location_X;
	// 地理位置经度
	private String Location_Y;
	// 地图缩放大小
	private String Scale;
	// 地理位置信息
	private String Label;

	/**
	 * Gets location x.
	 *
	 * @return the location x
	 */
	public String getLocation_X() {
		return Location_X;
	}

	/**
	 * Sets location x.
	 *
	 * @param location_X the location x
	 */
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	/**
	 * Gets location y.
	 *
	 * @return the location y
	 */
	public String getLocation_Y() {
		return Location_Y;
	}

	/**
	 * Sets location y.
	 *
	 * @param location_Y the location y
	 */
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	/**
	 * Gets scale.
	 *
	 * @return the scale
	 */
	public String getScale() {
		return Scale;
	}

	/**
	 * Sets scale.
	 *
	 * @param scale the scale
	 */
	public void setScale(String scale) {
		Scale = scale;
	}

	/**
	 * Gets label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return Label;
	}

	/**
	 * Sets label.
	 *
	 * @param label the label
	 */
	public void setLabel(String label) {
		Label = label;
	}
}
