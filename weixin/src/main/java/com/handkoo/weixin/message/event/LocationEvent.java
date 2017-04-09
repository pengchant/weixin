package com.handkoo.weixin.message.event;

/**
 * 上报地理位置事件
 * 
 * @author cp <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
 *         <FromUserName><![CDATA[fromUser]]></FromUserName>
 *         <CreateTime>123456789</CreateTime>
 *         <MsgType><![CDATA[event]]></MsgType>
 *         <Event><![CDATA[LOCATION]]></Event> <Latitude>23.137466</Latitude>
 *         <Longitude>113.352425</Longitude>
 *         <Precision>119.385040</Precision> </xml>
 *
 */
public class LocationEvent extends BaseEvent {
	// 地理位置纬度
	private String Latitude;
	// 地理位置经度
	private String Longitute;
	// 地理位置精度
	private String Position;

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitute() {
		return Longitute;
	}

	public void setLongitute(String longitute) {
		Longitute = longitute;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}
}
