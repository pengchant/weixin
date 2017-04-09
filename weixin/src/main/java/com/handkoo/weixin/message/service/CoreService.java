package com.handkoo.weixin.message.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handkoo.util.CommonUtil;
import com.handkoo.util.MessageUtil;
import com.handkoo.weixin.message.req.TextMessage;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import net.sf.json.JSONObject;

/**
 * 核心的消息处理层
 * 
 * @author cp
 *
 */
public class CoreService {

	private static final Logger logger = LoggerFactory.getLogger(CoreService.class);

	/**
	 * 处理request，进行消息的分发处理
	 * 
	 * @param request
	 *            servletrequest
	 * @return 返回处理后的xml报文字符串
	 */
	public static String processRequest(HttpServletRequest request) {
		// 需要返回的xml
		String respXml = null;
		// 返回的内容
		String respContent = "";
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 获取发送方
			String fromUserName = requestMap.get("FromUserName");
			// 获取接收方
			String toUserName = requestMap.get("ToUserName");
			// 获取消息的类型
			String msgType = requestMap.get("MsgType");
			// 控制台输出
			logger.info("#接受到消息fromusername" + fromUserName + "tousername:" + toUserName + ",msgtype:" + msgType);

			// 定义返回的消息(文本消息)
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 如果发送的文本
//			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//				respContent = "你发送的文本";
//			}
//			// 如果发送的图片
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//				respContent = "你发送的图片";
//			}
//			// 如果发送的语音
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//				respContent = "你发送的语音";
//			}
//			// 如果发送的视频
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
//				respContent = "你发送的是视频";
//			}
//			// 如果发送的地理位置
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
//				respContent = "你发送的是地理位置";
//			}
//			// 如果发送的连接
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//				respContent = "你发送的是链接";
//			}
			
			
			// 如果是推送过来的事件(微信服务器->开发者服务器)
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件的类型
				String eventType = requestMap.get("Event").toLowerCase();
				// 关注事件
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "你好欢迎关注~";
					String key = requestMap.get("EventKey");
					if (key.contains("qrscene_")) {
						key = key.substring(key.lastIndexOf("_") + 1);
						
						logger.info("获取到二维码中的字符串为：==============>" + key);

						logger.info("获取到扫描二维码的用户的openid为：==============>" + fromUserName);
						
						respContent = "你扫描了二维码,二维码中携带的编号为：" + key + ",扫描二维码的用户的openid为：" + fromUserName;
						
						if (!"0".equals(key)) {// 发送模板消息
							String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
									+ CommonUtil.token;
							String templateId = "HlMIjSLAyHrA95tvjKQL9-9gkxvsTKsTBQYirvItZDY";
							String urls = "http://www.xzit.edu.cn/";
							String jsonOjb = "{\"touser\":\"" + fromUserName + "\",\"template_id\":\"" + templateId
									+ "\", \"url\":\"" + urls + "\",\"data\":{\"mark\":" + "{\"value\":\"" + key
									+ "\",\"color\":\"#173177\"}}}";
							JSONObject result = CommonUtil.httpRequest(url, "POST", jsonOjb);
							logger.info("推送消息操作的结果:" + result);
						}
					}
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					respContent = "感谢一路陪伴~";
				}
				// 扫描二维码事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					String key = requestMap.get("EventKey");

					/**
					 * 
					 * 获取二维码中的字符串和扫描用户的openid
					 * 
					 * 
					 */
					logger.info("获取到二维码中的字符串为：==============>" + key);
					logger.info("获取到扫描二维码的用户的openid为：==============>" + fromUserName);
 
					respContent = "你扫描了二维码,二维码中携带的编号为：" + key + ",扫描二维码的用户的openid为：" + fromUserName;
					JSONObject jsonObject = CommonUtil.getUser(CommonUtil.token, fromUserName) ;
					String nickname= "";
					if(jsonObject!=null){
						nickname = jsonObject.getString("nickname");
					}
					respContent+=",昵称为："+nickname;
				}
				// 推送位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					respContent = "推送的位置!";
				}
				// 点击了菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					respContent = "点击了菜单!";
				} 
			} 
			textMessage.setContent(respContent);
			logger.info("----------------->" + respContent);
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
