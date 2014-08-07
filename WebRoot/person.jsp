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

<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="./static/style/custom.css">

<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script src="./dist/js/bootstrap.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container header-bg">
		<nav class="navbar navbar-default header-border header-height"
			role="navigation">

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<div class="row">

				              <div class="col-lg-6">
                <form class="navbar-form navbar-left" role="search" action="searchQuestion" method="post">
                <div class="input-group ">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="submit"> Search </button>
                  </span>
                  <input type="text" class="form-control" name="context">
                </div><!-- /input-group -->
               </form>
              </div><!-- /.col-lg-6 -->

				<div class="col-lg-6">

					<%
						if (uib != null) {
					%>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="index">首页</a>
						<li><a href="person.jsp"><%=uib.getUser_name() %></a>
						</li>
						<li><a href="#">消息</a>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Account <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="person.jsp">我的主页</a>
								</li>
								<li><a href="userLogOffServlet">退出</a>
								</li>
								<li class="divider"></li>
								<li><a href="#">建议反馈</a>
								</li>
							</ul></li>
					</ul>
					<%
						} else {
					%>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="index.jsp">首页</a>
						<li><a href="userLogin.jsp">登录</a>
						</li>
						<li><a href="userRegister.jsp">注册</a>
						</li>
					</ul>
					<%
						}
					%>
				</div>
			</div>
		</div>
		<!-- /.navbar-collapse --> </nav>
	</div>

	<hr class="hr-header">

	<div class="container main-content">
		<div class="row">

			<!-- left-->
			<div class="col-lg-9">
				<div class="row">
					<!-- person-->
					<div class="col-md-7">
						<div class="row fix-margin-top">
							<div class="col-md-8 ">
								<div class="info info-margin-left">
									<span><font size="+2"><%=uib.getUser_name() %></font> </span> <span
										class="glyphicon glyphicon-envelope info-link"> <%if(uib.getUser_email()!=null){ %><a><%=uib.getUser_email() %></a>
										<%}else{ %><font size="-1">没有邮箱信息，请<a>填写</a> </font> <%} %> </span> <span
										class="glyphicon glyphicon-link info-link"> <a href="http://www.0523.tk" target="_blank">http://www.0523.tk/</a>
									</span>
								</div>
								<div class="fix-margin-top">
									<a type="button" class="btn btn-default a-float-left border-0">
										<span class="glyphicon glyphicon-star"></span> 提问
										${countOfQuestion } </a> <a type="button"
										class="btn btn-default a-float-left border-0"> <span
										class="glyphicon glyphicon-user"></span> 回答 ${countOfAuswer }
									</a>
								</div>
							</div>
						</div>
					</div>
					<!-- person -->
				</div>

				<div class="bs-example bs-example-tabs fix-margin-top ">
					<ul id="myTab" class="nav nav-tabs nav-right">
						<li class=""><a href="#hot" data-toggle="tab">回答前10</span> </a></li>
						<li class="active"><a href="#new" data-toggle="tab">我的提问
								&nbsp;<span class="badge"> ${countOfQuestion }</span> </a>
						</li>
					</ul>
					<script type="text/javascript">
                    $('#myTab a').click(function (e) {
                      e.preventDefault()
                      $(this).tab('show')
                    })
                  </script>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade active in" id="new">
							<table class="table table-hover">
								<tr>
									<th>问题标题</th>
									<th>提问时间</th>
								</tr>
								<c:forEach items="${listQuestion }" var="question" begin="0"
									step="1" varStatus="class">
									<tr>
										<td><a
											href="getDetilQuestion?question_id=${question.question_id }">${question.question_title
												}</a></td>
										<td>${question.question_time }</td>
									</tr>
								</c:forEach>
							</table>
							 <div  style=text-align:center;>
	                  
							<c:choose>
								 <c:when test="${currentPage == 0 }">首页&nbsp;上一页&nbsp;</c:when>
								 <c:otherwise><a href="disscussPerson?currentPage=0">首页</a>&nbsp;<a href="disscussPerson?currentPage=${currentPage - 1}">上一页</a>&nbsp;</c:otherwise>
							</c:choose>
							<c:choose>
						        <c:when test="${currentPage >=  len  }">下一页&nbsp;末页&nbsp;</c:when>
						        <c:otherwise><a href="disscussPerson?currentPage=${currentPage + 1 }">下一页</a>&nbsp;<a href="disscussPerson?currentPage=-1">末页</a>&nbsp;</c:otherwise>
						    </c:choose>
	     				
     				</div>
						</div>

						<div class="tab-pane fade" id="hot">
		<table class="table table-hover">
								<tr>
									<th>回答简述</th>
									<th>回答时间</th>
								</tr>
								<c:forEach items="${listAnswer }" var="answer" begin="0"
									step="1" varStatus="class">
									<tr>
										<td><a
													href="getDetilQuestion?question_id=${answer.question_id }">${fn:substring(answer.answer_description,0,25)
													}......</a></td>
										<td>${answer.answer_time }</td>
									</tr>
								</c:forEach>
							</table>
						</div>

					</div>
				</div>
			</div>
			<!-- left-->

			<!-- right-->
			<div class="col-lg-3">

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
				<img src="./static/image/ex.png" alt="..."
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