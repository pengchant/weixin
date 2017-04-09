/*
 * File name:          JsAPISign.java
 * Copyright@Handkoo (China)
 * Editor:           JDK1.6.32
 */
package com.handkoo.weixin.common.entity;

/**
 * TODO: File comments
 * <p>
 * <p>
 * Author:           jamie
 * <p>
 * Date:           2017年3月7日
 * <p>
 * Time:           上午11:08:18
 * <p>
 * Director:         jamie
 * <p>
 * <p>
 */
public class JsAPISign {
	private boolean debug;

	private String appId;

	private String timestamp;

	private String nonceStr;

	private String signature;

	private String[] jsApiList;

	public JsAPISign(boolean debug, String appId, String timestamp, String nonceStr, String signature) {
		this.setDebug(debug);
		this.setAppId(appId);
		this.setTimestamp(timestamp);
		this.setNonceStr(nonceStr);
		this.setSignature(signature);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public boolean getDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String[] getJsApiList() {
		return jsApiList;
	}

	public void setJsApiList(String[] jsApiList) {
		this.jsApiList = jsApiList;
	}

}
