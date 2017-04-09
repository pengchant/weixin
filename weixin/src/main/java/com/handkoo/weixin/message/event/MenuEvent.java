package com.handkoo.weixin.message.event;

/**
 * 自定义菜单事件
 * 
 * @author cp <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
 *         <FromUserName><![CDATA[FromUser]]></FromUserName>
 *         <CreateTime>123456789</CreateTime>
 *         <MsgType><![CDATA[event]]></MsgType> <Event><![CDATA[CLICK]]></Event>
 *         <EventKey><![CDATA[EVENTKEY]]></EventKey> </xml>
 *
 */
public class MenuEvent extends BaseEvent {
	// 事件的KEY值
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
