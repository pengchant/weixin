package com.handkoo.util;
 
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

/**
 * 伴随项目启动和销毁的监听器
 * @author cp
 *
 */
public class ContextListener implements ServletContextListener{

	private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);
	
	private Timer timer = null;
			
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 timer = new Timer(true);
		 sce.getServletContext().log("定时器已经启动.......");
		 // 定时器每隔1个小时重新刷新token
		 timer.schedule(new MyTask(sce.getServletContext()), 0, 1*60*60*1000);
		 sce.getServletContext().log("已经添加刷新token的任务调度");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		 timer.cancel();
		 logger.info("------------定时器被销毁-----------");
		 sce.getServletContext().log("#定时器销毁#");
	}

}
