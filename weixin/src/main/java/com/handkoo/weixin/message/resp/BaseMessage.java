package com.handkoo.weixin.message.resp;

/**
 * 
 * @author cp（服务端在处理完业务逻辑后发送给普通的用户:服务器->微信服务器->用户）
 *
 */
public class BaseMessage {
	// 发送给普通的用户
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 创建的时间
	private String CreateTime;
	// 消息的类型
	private String MsgType;

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

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
