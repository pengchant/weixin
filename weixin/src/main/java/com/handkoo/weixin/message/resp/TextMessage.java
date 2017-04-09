package com.handkoo.weixin.message.resp;

/**
 * 文本的消息
 * @author cp
 *
 */
public class TextMessage extends BaseMessage {
	// 文本消息的具体内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
