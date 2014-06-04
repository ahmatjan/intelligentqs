<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<html>
<head>
	<title>登陆表单</title>
</head>

<body>
<center>
  <h1>管理者登陆 </h1>
<hr>
<form name="form1" method="post" action="login.jsp">
请输入您的用户名：<input type="text" name="name"><p>
请输入您的密码：<input type="password" name="password">
    <p>
      <input type="submit" name="Submit" value="　登　陆　">
      <input type="reset" name="Submit2" value="　重　写　">
  </form>
</center>	
</body>
</html>
