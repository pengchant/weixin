package com.handkoo.weixin.message.event;

/**
 * 基本事件:(用户对公共号进行操作，微信服务器会将对应的消息已xml的格式传递给公共号暴露的servlet:用户操作->微信服务器监听到事件->服务器)
 * @author cp
 *
 */
public class BaseEvent {
	// 开发者微信号
	private String ToUserName;
	// 发送方的账号
	private String FromUserName;
	// 事件创建的时间
	private long CreateTime;
	// 消息的类型(取消/关注事件,扫描带二维码事件,上报地理位置事件,自定义菜单事件)
	private String MsgType;
    // 时间类型(subscribe,unsubscribe,scan,location,click)
	private String Event;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

}
