package com.handkoo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.handkoo.util.CommonUtil;
import com.handkoo.weixin.common.entity.JsAPISign;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * jssdk的配置servlet
 * 
 * @author cp
 *
 */
public class JSSDKConfigServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(JSSDKConfigServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			//1、获取AccessToken  
		    String accessToken = CommonUtil.token;
		      
		    //2、获取Ticket  
		    String jsapi_ticket = CommonUtil.jsapiticket;  
		      
		    //3、时间戳和随机字符串  
		    String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
		    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  
		      
		    logger.info("accessToken:"+accessToken+"\njsapi_ticket:"+jsapi_ticket+"\n时间戳："+timestamp+"\n随机字符串："+noncestr);  
		      
		    //4、获取url  
		    String url=request.getParameter("regUrl");  
		     
		    //5、将参数排序并拼接字符串  
		    String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;  
		     
		    //6、将字符串进行sha1加密  
		    String signature =SHA1(str);  
		    logger.info("参数："+str+"\n签名："+signature); 
		 
		    // 发送到前台
		    JsAPISign jsAPISign = new JsAPISign(false, CommonUtil.appid, timestamp, noncestr, signature);
			out.print(JSON.toJSON(jsAPISign));
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		}
		out.flush();
		out.close();
	} 
	
	
	/**
	 * 签名算法
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {  
	    try {  
	        MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");  
	        digest.update(decript.getBytes());  
	        byte messageDigest[] = digest.digest();  
	        // Create Hex String  
	        StringBuffer hexString = new StringBuffer();  
	        // 字节数组转换为 十六进制 数  
	            for (int i = 0; i < messageDigest.length; i++) {  
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
	                if (shaHex.length() < 2) {  
	                    hexString.append(0);  
	                }  
	                hexString.append(shaHex);  
	            }  
	            return hexString.toString();  
	   
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	}  
}
