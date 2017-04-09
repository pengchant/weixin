package com.handkoo.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.handkoo.weixin.menu.entity.Menu;
import net.sf.json.JSONObject;

/**
 * 菜单创建的工具类
 * @author cp
 *
 */
public class MenuUtil {
	private static Logger logger = LoggerFactory.getLogger(MenuUtil.class);

	 
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
 
	public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
 
	public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * @param menu
	 * @param accessToken
	 * @return
	 */
	public static boolean createMenu(Menu menu, String accessToken) {
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		 
		String jsonMenu = JSONObject.fromObject(menu).toString();
	 
		logger.info("##########################"+jsonMenu);
		
		JSONObject jsonObject = CommonUtil.httpRequest(url, "POST", jsonMenu);

		logger.info(jsonObject.toString());
		
		if (jsonObject != null) {
			logger.info("------->errorCode:"+jsonObject.getString("errcode")+",errmsg:"+jsonObject.getString("errmsg"));
			String errorCode = jsonObject.getString("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (StringUtils.equals(errorCode, "0")) {
				result = true;
			} else {
				result = false;
				logger.error("创建菜单失败,errorcode:{} errormsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
 
	/**
	 * 查询菜单
	 * @param accesToken
	 * @return
	 */
	public static String getMenu(String accesToken) {
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accesToken);
	 
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		if (jsonObject != null) {
			result = jsonObject.toString();
		}
		return result;
	}
 
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		 
		JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
		if (jsonObject != null) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (errorCode == 0) {
				result = true;
			} else {
				result = false;
				logger.error("删除菜单失败,errorCode:{},errormsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

}
