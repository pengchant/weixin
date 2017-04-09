package com.handkoo.weixin.message.resp;

/**
 * 视频的实体类
 * @author cp
 *
 */
public class Video {
	
	// 视频的媒体编号
	private String MediaId;
	// 缩略图
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
