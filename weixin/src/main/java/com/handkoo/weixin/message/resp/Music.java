package com.handkoo.weixin.message.resp;

/**
 * 音乐的实体类
 * @author cp
 *
 */
public class Music {
	// 音乐的标题
	private String Title;
	// 描述
	private String Description;
	// 音乐的路径
	private String MusicUrl;
	// 高质量音乐(网络条件较好的情况下)
	private String HQMusicUrl;
	// 缩略图的媒体ID，通过上传多媒体文件而获取到ID
	private String ThumMediaId;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getThumMediaId() {
		return ThumMediaId;
	}

	public void setThumMediaId(String thumMediaId) {
		ThumMediaId = thumMediaId;
	}

}
