package com.handkoo.util;
 
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class QRCodeUtil {  
	
	private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);
	
	/**
	 * 获取二维码
	 * @param token token令牌
	 * @return	得到二维码的连接
	 */
	public static String getErWeiURL(String token,Integer qudaoCode){ 
		String ticket = null;
		String url = null;
		String scene = "";
		qudaoCode = qudaoCode==null?-1:qudaoCode;
		scene =(qudaoCode==-1)?"{\"scene_id\":\"\"}":"{\"scene_id\": "+qudaoCode+"}";
		if(token!=null&&StringUtils.isNotBlank(token)){
			logger.info("token"+token);
			try {
				// 1.获取ticket  
				String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\":"+scene+"}}";
			  
				JSONObject jsonObject = CommonUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token, "POST", json);
				logger.info("获取ticket--->"+jsonObject);
				if(jsonObject!=null){
					ticket = jsonObject.getString("ticket");
				}   
				// 2.获取二维码json
				if(ticket!=null){
					url="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;		
					logger.info("返回的路径:"+url);
				}
			} catch (Exception e) {
				logger.error(MyErrorUtils.getErrorStack(e));
			}
		} 
		return url;
	}  
	
	/**
	 * 获取永久的二维码
	 * @param token		token令牌
	 * @param sceneStr	场景的字符串的值
	 * @return
	 */
	public static String getYongjiuQrCode(String token,String sceneStr){
		String url = null;
		String ticket = null;
		if(StringUtils.isNotBlank(token)&&StringUtils.isNotBlank(sceneStr)){
			try {
				// 1.获取ticket
				String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+sceneStr+"\"}}}";
				JSONObject jsonObject = CommonUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token, "POST", json);
				logger.info("获取到的ticket:"+jsonObject);
				if(jsonObject!=null){
					ticket = jsonObject.getString("ticket");
				}
				// 2.获取二维码json
				if(ticket!=null){
					url="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;		
					logger.info("返回的路径:"+url);
				}
			} catch (Exception e) {
				logger.info("获取永久二维码失败....");
			}
		}
		return url;
	}
	
	
	
	
	
	
	
	
	
 
	
	
}
