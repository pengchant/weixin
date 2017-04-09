package com.handkoo.weixin.message.resp;

/**
 * 音乐消息的实体
 * @author cp
 *
 */
public class MusicMessage extends BaseMessage{
 
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
