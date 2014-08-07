<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<form action="updatePasswordServlet" method="post" id="updatePasswordForm" name="updatePasswordForm">
    		<table>
    			<tr>
    				<td width="200px" align="right">原密码</td>
    				<td><input type="password" id="oldPassword" name="oldPassword"/></td>	
    			</tr>
    			<tr>
    				<td align="right">新密码</td>
    				<td><input type="password" id="newPassword" name="newPassword"/></td>	
    			</tr>
    			<tr>
    				<td align="right">确认新密码</td>
    				<td><input type="password" id="sureNewPassword" name="sureNewPassword"/></td>	
    			</tr>
    			<tr>
    				<td align="right">${Msg }</td>
    				<td><input type="submit" value="确认修改"/></td>	
    			</tr>
    		</table>
    	</form>
  </body>
</html>
