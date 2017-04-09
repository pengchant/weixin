package com.handkoo.weixin.message.req;

/**
 * 图片的消息
 * @author cp
 *
 */
public class ImageMessage extends BaseMessage {
	
	// 图片的链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
