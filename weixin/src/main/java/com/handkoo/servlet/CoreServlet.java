package com.handkoo.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handkoo.util.SignUtil;
import com.handkoo.weixin.message.service.CoreService;

/**
 * 请求处理的核心类
 * 
 * @author cp
 *
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CoreServlet.class);

	/**
	 * 请求校验(确认来自微信服务器)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 微信加密的签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发送过来的请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取微信服务器的签名，时间戳和随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		PrintWriter out = response.getWriter();
		logger.info("signature:" + signature + "timestamp:" + timestamp + "nonce:" + nonce);
		// 验证是否来自微信服务器的请求
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("请求校验成功!");
			String respXml = CoreService.processRequest(request);
			logger.info("返回给微信服务器的xml:" + respXml);
			out.print(respXml);
		}
		out.close();
		out = null;
	}

	
}
