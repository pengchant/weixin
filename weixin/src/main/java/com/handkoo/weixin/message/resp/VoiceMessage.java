package com.handkoo.weixin.message.resp;
 
/**
 * 语音的消息
 * @author cp
 *
 */
public class VoiceMessage extends BaseMessage {
	
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
