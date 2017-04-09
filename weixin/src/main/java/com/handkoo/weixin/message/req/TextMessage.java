package com.handkoo.weixin.message.req;

/**
 * 文本消息
 * @author cp
 *
 */
public class TextMessage extends BaseMessage {
	// 文本消息的内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
