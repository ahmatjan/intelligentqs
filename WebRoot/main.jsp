<%@page import="cn.com.beans.UserInfoBean"%>
<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserInfoBean userBean = (UserInfoBean)session.getAttribute("userBean");
%>
<html>
<head>
	<title>在线问答系统</title>
</head>

<body>
<center>
  <h1>在线问答系统 </h1>
<hr size="3">
<%=userBean.getUser_name() %>
<%=userBean.getUser_mark() %>
<%=userBean.getUser_email() %>
<table width="80%" border="1" align="center">
  <tr>
      <td width="9%">记录号</td>
      <td width="40%">问题</td>
      <td width="10%">点击数</td>  
      <td width="14%">回答人数</td>
      <td width="27%">日期</td>
  </tr>


  <tr>
      <td width="9%"></td>
      <td width="40%">
	  	<a href="show.jsp?">
		  
	    </a>
	  </td>
      <td width="10%"></td>
      <td width="14%"></td>
      <td width="27%"></td>
  </tr>

</table>
<hr>
<a href="main.jsp?page=">上一页</a>
&nbsp;&nbsp;&nbsp;&nbsp;

<a href="main.jsp?page=">下一页</a>

<p><a href="add.jsp">增加问题</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="login.jsp">管理员登陆</a>

</center>
</body>
</html>
