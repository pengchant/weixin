package com.handkoo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handkoo.weixin.common.entity.Token;

import net.sf.json.JSONObject;

public class CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	// app的编号
	public static String appid = "";
	
	// app的密钥
	public static String appsecret = "";
	
	// 开发者平台设置的token字符
	public static String tokenstr = "";
	
	// token
	public static String token = "";
	
	// jsapiticket
	public static String jsapiticket="";
	
	static{
		try {
			InputStream inputStream = CommonUtil.class.getResourceAsStream("/user-settings.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			appid = properties.getProperty("appid");
			appsecret = properties.getProperty("appsecrect");
			tokenstr = properties.getProperty("tokenstr");
		} catch (Exception e) {
			logger.error(MyErrorUtils.getErrorStack(e));
		} 
	}

	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

	public final static String user_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	
	public final static String jsapiticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/**
	 * 发起https请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
		 
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 发起请求
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
		 
			conn.setRequestMethod(requestMethod);
 
			// 写入要传递的数据
			if (outputStr != null) {
				OutputStream outputStream = conn.getOutputStream();
			 
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

		    // 获取返回的数据
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			} 
			
			// 关闭流
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error("连接错误:{}", ce);
		} catch (Exception e) {
			logger.error("https访问异常:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取token
	 * @param appid			appid
	 * @param appsecret		appsecret
	 * @return
	 */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("SECRET", appsecret);

		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);

		if (jsonObject != null) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				token = null;
				logger.error("获取token失败!,errorcode:{} errormsg:{}", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	/**
	 * 获取jspaiticket
	 * @param appid
	 * @param token
	 * @return
	 */
	public static String getJSAPITicket(String token){
		String ticket = "";
		String url = jsapiticket_url.replace("ACCESS_TOKEN", CommonUtil.token);
		JSONObject jsonObject = httpRequest(url, "GET", null);
		if(jsonObject!=null){
			try {
				ticket = jsonObject.getString("ticket");
				logger.info("获取到的jsticket为===>"+ticket);
			} catch (Exception e) {
				logger.info("获取jsapiticket异常");
			}
		}
		return ticket;
	}
	 
	/**
	 * 获取用户json数据
	 * @param token
	 * @param openid
	 * @return
	 */
	public static JSONObject getUser(String token, String openid) {
		String url = CommonUtil.user_url.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		return httpRequest(url, "POST", null);
	}

}
