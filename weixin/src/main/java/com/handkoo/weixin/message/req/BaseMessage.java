package com.handkoo.weixin.message.req;

/**
 * 消息的基类（普通用户发送消息给开发者:用户主动发送消息->微信服务器->开发者服务器）
 * @author cp
 *
 */
public class BaseMessage {
	// 开发者微信号
	private String ToUserName;
	// 普通用户的微信id
	private String FromUserName;
	// 消息的创建时间
	private long CreateTime;
	// 消息的类型(text/image/location/link/voice)
	private String MsgType;
	// 消息的ID(64位整型)
	private long MsgId;

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

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

}
