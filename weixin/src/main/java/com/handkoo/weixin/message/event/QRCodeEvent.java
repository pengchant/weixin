package com.handkoo.weixin.message.event;

/**
 * 扫描带参数的二维码事件
 * 
 * @author cp 未关注： <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 *         <FromUserName><![CDATA[FromUser]]></FromUserName>
 *         <CreateTime>123456789</CreateTime>
 *         <MsgType><![CDATA[event]]></MsgType>
 *         <Event><![CDATA[subscribe]]></Event>
 *         <EventKey><![CDATA[qrscene_123123]]></EventKey>
 *         <Ticket><![CDATA[TICKET]]></Ticket> </xml> 
 *         已经关注：
 *         <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
 *         <FromUserName><![CDATA[FromUser]]></FromUserName>
 *         <CreateTime>123456789</CreateTime>
 *         <MsgType><![CDATA[event]]></MsgType> <Event><![CDATA[SCAN]]></Event>
 *         <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
 *         <Ticket><![CDATA[TICKET]]></Ticket> </xml>
 *
 */
public class QRCodeEvent extends BaseEvent {

	private String EventKey;

	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
