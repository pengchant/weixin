package com.handkoo.weixin.message.req;


/**
 * 语音消息
 * @author cp
 *
 */
public class VoiceMessage extends BaseMessage {
	// 媒体的ID
	private String MediaId;
	// 语音的格式
	private String Format;
	// 语音识别的结果
	private String Recognition;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

}
