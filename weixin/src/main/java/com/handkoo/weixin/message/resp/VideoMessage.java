package com.handkoo.weixin.message.resp;

/**
 * 视频消息
 * @author cp
 *
 */
public class VideoMessage extends BaseMessage {
 
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
