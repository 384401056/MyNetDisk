<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<h1>我的网盘</h1><hr>
    <a href="${pageContext.request.contextPath }/upload.jsp">上传文件</a><br>
    <a href="${pageContext.request.contextPath }/servlet/DownListServlet">文件列表</a>
  </body>
</html>
