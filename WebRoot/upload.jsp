<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <h3>上传页面</h3><br>
    <form action="${pageContext.request.contextPath }/servlet/uploadServlet" method="POST" enctype="multipart/form-data">
    	选择上传文件:<input type="file" name="file1"/><br><br>
    	描述信息:<textarea rows="5" cols="45" name="description"></textarea><br><br>
    	<input type="submit" value="上传文件"/> 
    </form>
  </body>
</html>
 