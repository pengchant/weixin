package com.handkoo.servlet;

import com.handkoo.util.CommonUtil;

import net.sf.json.JSONObject;

public class TestMuBan {
	
	public static void main(String[] args){
		CommonUtil.token = CommonUtil.getToken(CommonUtil.appid, CommonUtil.appsecret).getAccessToken();
		String step1 = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+CommonUtil.token;
		String data = "{\"industry_id1\":\"2\",\"industry_id2\":\"9\"}";
		JSONObject jsonObject = CommonUtil.httpRequest(step1, "POST", data);
		System.out.println(jsonObject+""+CommonUtil.token);
	}
}
