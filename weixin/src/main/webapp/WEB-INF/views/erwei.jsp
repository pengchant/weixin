<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码生成</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/erwei.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/erwei.js"></script>
</head>
<body> 
	<div class="wrapper">
		请输入渠道编号:<input type="text" id="code" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="create" value="生成二维码"/>
		<img src="${erweiURL}" alt="二维码" id="imgShower"/>
		<br/>
		<a id="downloader" href="${pageContext.request.contextPath}/QRCodeServlet?op=ewDownload&url=${erweiURL}" class="downloadEW">下载二维码</a> 
	</div> 
</body>
</html>