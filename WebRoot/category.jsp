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
	//HttpSession session =  (HttpSession)request.getSession(); 
	UserInfoBean uib = (UserInfoBean) session
			.getAttribute("userBean");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>发现Category</title>

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
						<form class="navbar-form navbar-left" role="Search"
							action="searchQuestion" method="post">
							<div class="input-group ">
								<span class="input-group-btn">
									<button class="btn btn-default" type="button">LOGO</button>
								</span> <input type="text" class="form-control"
									placeholder="提问之前不妨搜索试试？" value="" name="context"
									value="${searchKeyWords }"> <span
									class="input-group-btn">
									<button class="btn btn-default" type="submit">
										<span class="glyphicon glyphicon-search"></span>&nbsp;搜索
									</button>
								</span>
							</div>
							<!-- /input-group -->
						</form>
					</div>
					<!--col-lg-6-->

					<div class="col-lg-3">
						<ul class="nav navbar-nav navbar-left">
							<li><a href="index">主页</a></li>
							<li><a href="initCategory">发现</a></li>
						</ul>
					</div>

					<div class="col-lg-3">
						<%if(uib !=  null) {%>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">消息 <span class="badge">0</span></a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <img src="./static/image/git.png"
									alt="..." class="person-img img-circle">&nbsp;&nbsp;<%=uib.getUser_name()%><b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="GetPersionInfoServlet">我的主页</a></li>
									<li><a href="#">帐号设置</a></li>
									<li><a href="userLogOffServlet">退出</a></li>
									<li class="divider"></li>
									<li><a href="#">建议反馈</a></li>
								</ul></li>
						</ul>
						<%} else{ %>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="index">首页</a></li>
							<li><a href="userLogin.jsp">登录</a></li>
							<li><a href="userRegister.jsp">注册</a></li>
						</ul>
						<%} %>
					</div>
				</div>
			</div>
			<!-- navbar-collapse -->
		</nav>
	</div>
	<!--end of navbar-->

	<div class="container main-content">

		<hr class="hr-header">

		<div class="row">
			<!-- left-->
			<div class="col-lg-9">
				<div class="bs-example bs-example-tabs">
					<!--问题与标签切换-->
					<ul id="myTab" class="nav nav-tabs">
						<li href="#tag" class=""><a href="#" data-toggle="tab">标签导航</a>
						</li>
					</ul>
					<c:forEach items="${listCategory }" var="category" begin="0"
						step="1" varStatus="status">
						<div id="myTabContent" class="tab-content nav">
							<div>
								<div class="col-lg-4" style="width: 100%;">
									<div class="row tag-list">
										<section>
											<h4 class="col-lg-12">${category.tags_categories_name }</h4>
											<ul class="col-lg-12">
												<c:forEach items="${listAll }" var="listTags" begin="0"
													step="1" varStatus="stu">
													<!-- 比较标签分类ID 与 便签所属分类ID是否一致 -->
													<c:if
														test="${ category.tags_categories_id == listTags.tags_categories_id }">
														<!-- 属于该分类者输出该分类下所有标签 -->
														<a
															href="getTagsDetail?tags_categories_id=${category.tags_categories_id}&tags_name=${listTags.tags_name }&tags_id=${listTags.tags_id }"><span
															class="mark-p label label-default">${listTags.tags_name }</span></a>
													</c:if>

												</c:forEach>
											</ul>
										</section>
									</div>
								</div>
								<div class="col-lg-12">
									<hr class="hr-category">
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- left-->

			<!-- right-->
			<div class="col-lg-3" style="margin-top: 39px;">
				<!-- hot-question -->
				<div class="row hot-question">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a href="#" class="panel-title pull-right border-0"></a>
								<h3 class="panel-title">热门标签</h3>
							</div>
							<div class="panel-body">
								<div class="col-md-12">
									<c:forEach items="${listTags }" var="tag" begin="0" step="1"
										varStatus="class">
										<span class="mark-p label label-default">${tag.tags_name }</span>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>

					<!-- hot-user -->
					<div class="row hot-user">
						<div style="margin-left: 15px;">
							<div class="col-lg-8">
								<h4>风云人物</h4>
							</div>
							<div class="col-lg-4">
								<p></p>
								<p class="more">
									<a href="#">更多</a>
								</p>
							</div>
							<div class="col-lg-12 related-list">
								<ul class="list-unstyled row">
									<li>
										<div class="row">
											<div class="col-md-3 col-md-offset-1">
												<img src="./static/image/git.png" alt=""
													class="qa-img img-circle">
											</div>
											<div class="col-md-8">
												<p>
													<c:forEach items="${listHotUser }" var="user" begin="0"
														step="1" varStatus="class" end="0">
														<a
															href="/GetPersionInfoServlet?user_id=${user.userInfo.user_id }">${user.userInfo.user_name }</a>
														<br>
														<a href="#">积分：${user.userInfo.user_mark }</a>
														<br>
														<a
															href="/GetPersionInfoServlet?user_id=${user.userInfo.user_id }">回答问题数：${user.ansTotal }</a>
													</c:forEach>
												</p>
											</div>

										</div>
									</li>
									<c:forEach items="${listHotUser }" var="user" begin="1"
										step="1" varStatus="class">
										<li><span class="col-md-6"><a
												href="/GetPersionInfoServlet?user_id=${user.userInfo.user_id }"
												target="_blank">${user.userInfo.user_name }</a></span><span
											class="col-md-6">回答问题数：${user.ansTotal }</span></li>
									</c:forEach>
								</ul>

							</div>
						</div>
					</div>
					<!-- end of hot-user -->

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
				<p>
					Power by hnust_qa current version 1.0
					<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1252964295'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s19.cnzz.com/z_stat.php%3Fid%3D1252964295%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
				</p>
			</div>
		</div>
		<!-- 分享 Button BEGIN -->
		<script type="text/javascript"
			src="http://v3.jiathis.com/code/jiathis_r.js?uid=1406205777695855&move=0&amp;btn=r3.gif"
			charset="utf-8"></script>
		<!-- 分享 Button END -->
</body>
</html>
