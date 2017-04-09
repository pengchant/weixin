package com.handkoo.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.handkoo.util.CommonUtil;
import com.handkoo.util.MyErrorUtils;
import com.handkoo.util.QRCodeUtil;

 
public class QRCodeServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(QRCodeServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("op");
		if ("ewIndex".equals(operation)) { 
			String url = QRCodeUtil.getErWeiURL(CommonUtil.token, null);
			request.setAttribute("erweiURL", url);
			request.getRequestDispatcher("/WEB-INF/views/erwei.jsp").forward(request, response);
		} else if ("ewDownload".equals(operation)) {// 下载二维码
			InputStream inputStream = null;
			String fileName = "handkoo" + System.currentTimeMillis() + ".jpg";
			int len = 0;
			String url = request.getParameter("url");
			try {
				// 连接到远程服务器
				URL urls = new URL(url);
				URLConnection urlConnection = urls.openConnection();
				inputStream = urlConnection.getInputStream();
				// 获取当前的输出流
				response.setContentType("img/jpg");
				response.setHeader("Location", fileName);
				response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
				OutputStream responseOS = response.getOutputStream();
				// 将远程的输入流写到response的输出流中去
				byte[] buffer = new byte[4028];
				while ((len = inputStream.read(buffer)) != -1) {
					responseOS.write(buffer, 0, len);
				}
				inputStream.close();
				responseOS.flush();
				responseOS.close();
			} catch (Exception e) {
				logger.error(MyErrorUtils.getErrorStack(e));
			}
		} else if ("queryErWei".equals(operation)) {// 查询二维码
			String code = request.getParameter("code");
			response.setContentType("text/html;charset=utf-8"); 
			PrintWriter out = response.getWriter();
			try {
				if (code != null) { 
					String url = QRCodeUtil.getYongjiuQrCode(CommonUtil.token, code); 
					out.println(JSON.toJSONString(url)); 
				}
			} catch (Exception e) { 
				out.println(JSON.toJSONString("-1"));
			}
			out.flush();
			out.close();
		}
	}
}
