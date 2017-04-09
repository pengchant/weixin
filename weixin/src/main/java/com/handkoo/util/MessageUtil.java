package com.handkoo.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.handkoo.weixin.message.req.ImageMessage;
import com.handkoo.weixin.message.req.TextMessage;
import com.handkoo.weixin.message.req.VoiceMessage;
import com.handkoo.weixin.message.resp.Article;
import com.handkoo.weixin.message.resp.MusicMessage;
import com.handkoo.weixin.message.resp.NewsMessage;
import com.handkoo.weixin.message.resp.VideoMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信公共平台消息工具
 * 
 * @author cp
 *
 */
public class MessageUtil {
	// 文本消息
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// 图片消息
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// 语音消息
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// 视频消息
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	// 地理位置消息
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// 链接消息
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	// 请求类型：事件推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	// 订阅事件
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 取消关注
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 扫描二维码
	public static final String EVENT_TYPE_SCAN = "scan";
	// 发送地理位置
	public static final String EVENT_TYPE_LOCATION = "location";
	// 点击自定义菜单
	public static final String EVENT_TYPE_CLICK = "click";

	// 响应消息：文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// 响应消息：图片
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息：语音
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息:视频
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息：音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息：图文
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 将微信服务器推送过来的xml字符串转化为map键值对
	 * 
	 * @param request
	 *            servlet请求
	 * @return 返回解析之后的结果
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		for (Element e : elements) {
			map.put(e.getName(), e.getText());
			System.out.println("解析xml：" + e.getName() + "," + e.getText());
		}
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * 定义xstream对象
	 */
	private static XStream xStream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {

				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 将text转化为xml
	 * @param textMessage		文本消息对象
	 * @return
	 */
	public static String messageToXml(TextMessage textMessage) {
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	/**
	 * 响应消息：将图片转化为xml
	 * @param imageMessage		图片消息对象
	 * @return
	 */
	public static String messageToXml(ImageMessage imageMessage) {
		xStream.alias("xml", imageMessage.getClass());
		return xStream.toXML(imageMessage);
	}

	/**
	 * 响应消息：将语音转化为xml
	 * @param voiceMessage		语音消息对象
	 * @return
	 */
	public static String messageToXml(VoiceMessage voiceMessage) {
		xStream.alias("xml", voiceMessage.getClass());
		return xStream.toXML(voiceMessage);
	}

	/**
	 * 响应消息：将视频转化为xml
	 * @param videoMessage		视频消息对象
	 * @return
	 */
	public static String messageToXml(VideoMessage videoMessage) {
		xStream.alias("xml", videoMessage.getClass());
		return xStream.toXML(videoMessage);
	}

	/**
	 * 响应消息：将音乐转化为xml
	 * @param musicMessage		音乐消息对象
	 * @return
	 */
	public static String messageToXml(MusicMessage musicMessage) {
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
	}

	/**
	 * 响应消息：将图文转化为xml
	 * @param newsMessage		图文消息对象
	 * @return
	 */
	public static String messageToXml(NewsMessage newsMessage) {
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new Article().getClass());
		return xStream.toXML(newsMessage);
	}

}
