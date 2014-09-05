<%@page import="cn.com.beans.UserInfoBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserInfoBean uib = (UserInfoBean) session
			.getAttribute("userBean");
	request.getAttribute("role");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>个人主页</title>
<!-- Bootstrap core CSS -->
<link href="./dist/css/bootstrap.css" rel="stylesheet">
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="./static/style/custom.css">
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon">
<!-- <script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script> -->
<script src="js/jquery.min.js"></script>
<script src="./dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/question/index.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
		<div class="container header-bg">
      <nav class="navbar navbar-default header-border header-height" role="navigation">
        
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <div class="row">

            <div class="col-lg-6">
              <form class="navbar-form navbar-left" role="Search" action="searchQuestion" method="post">
                <div class="input-group ">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button">LOGO</button>
                  </span>
                  <input type="text" class="form-control" placeholder="提问之前不妨搜索试试？" value="" name="context" value="${searchKeyWords }">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                      <span class="glyphicon glyphicon-search"></span>&nbsp;搜索
                    </button>
                  </span>
                </div><!-- /input-group -->
              </form>
            </div><!--col-lg-6-->

            <div class="col-lg-3">
              <ul class="nav navbar-nav navbar-left">
                <li><a href="index">主页</a></li>
                <li><a href="category.jsp">发现</a></li>
              </ul>
            </div>

            <div class="col-lg-3">
            <%if(uib !=  null) {%>
               <ul class="nav navbar-nav navbar-right">
                <li><a href="getMessage" id="message">消息 <span class="badge"></span></a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <img src="./static/image/git.png" alt="..." class="person-img img-circle">&nbsp;&nbsp;<%=uib.getUser_name()%><b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="GetPersionInfoServlet">我的主页</a></li>
                    <li><a href="#">帐号设置</a></li>
                    <li><a href="userLogOffServlet">退出</a></li>
                    <li class="divider"></li>
                    <li><a href="#">建议反馈</a></li>
                  </ul>
                </li>
              </ul>
              <script src="js/question/message.js"></script>
              <%} else{ %>
              		<ul class="nav navbar-nav navbar-right">
							<li><a href="index">首页</a>
							</li><li><a href="userLogin.jsp">登录</a></li>
							<li><a href="userRegister.jsp">注册</a></li>
						</ul>
			<%} %>              
            </div>
          </div>
        </div><!-- navbar-collapse -->
      </nav>
    </div>

	<hr class="hr-header">

	<div class="container main-content">
		<div class="row">

			<!-- left-->
			<div class="col-lg-9">
			<h4>消息</h4>
			<hr>
				<div class="row" id="messages">
					<ul>
					</ul>
				</div>
			</div>
			<!-- left-->

			<!-- right-->
			<div class="col-lg-3">

				<!-- person-->
				<div class="row fix-margin-top">
					 <%if(uib !=  null) {%>
						<div class="col-md-7" id="user_id" value="<%=uib.getUser_id() %>">
							<img src="images/git.png" alt="" class="head-img img-thumbnail">
						</div>
						<div class="col-md-5 follow">
							<a type="button" class="btn btn-default"  id="m_question"> <span
								class="glyphicon glyphicon-question-sign"></span>问
								&nbsp;&nbsp;&nbsp;题&nbsp;<!-- <span id="questions"></span> --></a>
							<a type="button" class="btn btn-default" id="m_hava_solve"> <span
									class="glyphicon glyphicon-ok"></span>解决 <!-- <span
									id="solve"></span> --> </a> 
							<a type="button" class="btn btn-default" id="m_follow"> <span
									class="glyphicon glyphicon-heart"></span>关&nbsp;&nbsp;&nbsp;注&nbsp;<!--  <span
									id="stars">--></span>
							</a>
						</div>
					<%} %>

				</div>
				<!-- person -->
				<!-- mark-->
				<div class="row follow">
					<div class="col-md-12">
						<ul class="nav nav-pills">
							<li class="active"><a href="#">Tags</a></li>
						</ul>

						<span class="mark-p label label-default">Default</span> <span
							class="mark-p label label-default">Default Default</span> <span
							class="mark-p label label-default">Default</span> <span
							class="mark-p label label-default">Default Default</span> <span
							class="mark-p label label-default">Default</span>
					</div>
				</div>
				<!-- mark -->
				<img src="images/logo.png" alt="..."
					class="img-thumbnail fix-margin-top">

			</div>
			<!-- right-->

		</div>
	</div>
	<!-- main-content end-->
	<hr class="hr-header">

	<!-- foot-->
	<div class="foot">
		<div class="container">
			<p></p>
			<p>Power by hnust_qa current version 1.0</p>
		</div>
	</div>

</body>
</html>