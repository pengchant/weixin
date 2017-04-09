package com.handkoo.weixin.message.resp;

/**
 * 文章的实体类
 * @author cp
 *
 */
public class Article { 
	// 标题
	private String Title; 
	// 描述
	private String Description;
	// 图片的路径
	private String PicUrl;
	// 点击图文消息跳转的链接
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description==null?"":Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl==null?"":PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url==null?"":Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
