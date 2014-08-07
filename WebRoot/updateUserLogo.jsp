<%@page import="cn.com.beans.UserInfoBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserInfoBean userBean = (UserInfoBean)session.getAttribute("userBean");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更换头像</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	font-size: 14px;
}

input {
	vertical-align: middle;
	margin: 0;
	padding: 0
}

.file-box {
	position: relative;
	width: 340px
}

.txt {
	height: 22px;
	border: 1px solid #cdcdcd;
	width: 145px;
}

.btn {
	background-color: #FFF;
	border: 1px solid #CDCDCD;
	height: 24px;
	width: 70px;
}

.file {
	position: absolute;
	top: 0;
	right: 80px;
	height: 24px;
	filter: alpha(opacity :   0);
	opacity: 0;
	width: 260px
}
</style>
  </head>
  
  <body>
  <center>
    <form action="userLogoUploadServlet" method="post" enctype="multipart/form-data">
	<div class="file-box">
		
			<input type='text' name='textfield' id='textfield' class='txt' /> 
			<input type='button' class='btn' value='浏览...' />
			<input type="file" name="pUrl" class="file" id="fileField" size="28"
				onchange="document.getElementById('textfield').value=this.value" /><br/><br/>
			<input type="submit" value="上传"/>
	</div>
	</form>
	</center>
  </body>
</html>
