package com.handkoo.weixin.message.resp;

/**
 * 图片的消息
 * @author cp
 *
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		this.Image = image;
	}

}
