package com.handkoo.util;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
 
public class MyTask extends TimerTask{

	private static boolean isRunning = false;
	
	private ServletContext context = null;
	
	public MyTask() {
		super();
	}
	
	public MyTask(ServletContext context){
		this.context = context;
	}
	
	@Override
	public void run() {
		if(!isRunning){
			isRunning = true;
			context.log("=================================开始执行任务====================================");
			// 刷新token
			try {
				context.log("刷新token,和jsticket!");
				CommonUtil.token = CommonUtil.getToken(CommonUtil.appid, CommonUtil.appsecret).getAccessToken();
				CommonUtil.jsapiticket = CommonUtil.getJSAPITicket(CommonUtil.token);
				context.log("获取到的token为："+CommonUtil.token+",获取到的jsapiticket为："+CommonUtil.jsapiticket);
			} catch (Exception e) {
				context.log("刷新token和jsapiticket失败");
			}
			context.log("=================================执行任务结束====================================");
			isRunning = false;
		}else{
			context.log("上一次任务还未执行结束...");
		}
	}
	
}
