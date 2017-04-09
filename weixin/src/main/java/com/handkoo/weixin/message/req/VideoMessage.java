package com.handkoo.weixin.message.req;

/**
 * 视频的消息
 * @author cp
 *
 */
public class VideoMessage extends BaseMessage {
	// 视频消息的媒体ID
	private String MediaId;
	// 缩略图的媒体ID
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
