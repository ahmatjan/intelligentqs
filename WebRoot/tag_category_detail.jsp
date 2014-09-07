
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
<title>tag_detail</title>

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
							<li><a href="getMessage" id="message">消息 <span
									class="badge"></span></a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <img src="./static/images/git.png"
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
						<script src="js/question/message.js"></script>
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
				<div class="row tag-title">
					<div class="col-lg-2">
						<img src="images/logo.png" alt="..."
							class="qa-img img-circle tag-img">
					</div>
					<div class="col-lg-10">
						<h4>${tags_name }</h4>
						<p>${tagsDec }</p>
					</div>
				</div>

				<div class="bs-example bs-example-tabs">

					<ul id="myTab" class="nav nav-tabs nav-right">
						<li href="#hot" class=""><a href="#" data-toggle="tab">热门问题</a></li>
						<li href="#new" class="active"><a href="#" data-toggle="tab">最新问题</a>
						</li>
					</ul>
					<script type="text/javascript">
                    $('#myTab li').click(function (e) {
                      e.preventDefault()
                      $(this).tab('show')
                    })
                  </script>
					<div id="myTabContent" class="tab-content">


						<div class="tab-pane fade active in" id="new">
							<ul class="qa-list">

								<!--  content list -->
								<c:forEach items="${listQuestionTime }" var="question" begin="0"
									step="1" varStatus="class">
									<!--  content list -->
									<li class="row">
										<div class="col-md-2">
											<div class="btn btn-primary btn-lg active">
												<span class="glyphicon glyphicon-comment"></span>
												${question.countOfAnswers } <br>
											</div>
										</div>

										<div class="col-md-8 qa-margin-left">
											<p>
												<a target="_blank"
													href="getDetilQuestion?question_id=${question.questionBean.question_id }">${question.questionBean.question_title }</a>
											</p>${fn:substring(mashup.who,0,20)}
											<p>
											<pre>${fn:escapeXml(question.questionBean.question_description)}...</pre>
											</p>
											<div class="qa-tags">
												<span class="glyphicon glyphicon-tags"> </span> <a href="#"><span
													class="badge">${question.questionBean.question_tags}</span></a>
												&nbsp;&nbsp; <span class="glyphicon glyphicon-eye-open">
												</span> <span class="badge">${question.vpOfQuestion }</span>
												&nbsp;&nbsp; <span class="glyphicon glyphicon-time">
												</span> <span class="badge">${question.questionBean.question_time }</span>
											</div>
										</div>

										<div class="col-md-2">
											<a
												href="/GetPersionInfoServlet?user_id=${question.questionBean.question_user_id }"><img
												src="images/git.png" alt="..." class="qa-img img-circle"></a>
										</div>
									</li>
									<!--  content list end-->
								</c:forEach>
							</ul>

						</div>
						<div class="tab-pane fade" id="hot">

							<ul class="qa-list">

								<!--  content list -->

								<c:forEach items="${listQuestionHot }" var="question" begin="0"
									step="1" varStatus="class">
									<!--  content list -->
									<li class="row">
										<div class="col-md-2">
											<div class="btn btn-primary btn-lg active">
												<span class="glyphicon glyphicon-comment"></span>
												${question.countOfAnswers } <br>
											</div>
										</div>

										<div class="col-md-8 qa-margin-left">
											<p>
												<a target="_blank"
													href="getDetilQuestion?question_id=${question.questionBean.question_id }">${question.questionBean.question_title }</a>
											</p>
											<p>
											<pre>${fn:substring(question.questionBean.question_description,0,50)}...</pre>
											</p>
											<div class="qa-tags">
												<span class="glyphicon glyphicon-tags"> </span> <a href="#"><span
													class="badge">${question.questionBean.question_tags}</span></a>
												&nbsp;&nbsp; <span class="glyphicon glyphicon-eye-open">
												</span> <span class="badge">${question.vpOfQuestion }</span>
												&nbsp;&nbsp; <span class="glyphicon glyphicon-time">
												</span> <span class="badge">${question.questionBean.question_time }</span>
											</div>
										</div>

										<div class="col-md-2">
											<a
												href="/GetPersionInfoServlet?user_id=${question.questionBean.question_user_id }"><img
												src="images/git.png" alt="..." class="qa-img img-circle"></a>
										</div>
									</li>
									<!--  content list end-->
								</c:forEach>

								<!--  content list end-->

							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- left-->

			<!-- right-->
			<div class="col-lg-3">
				<!--follow-->
				<div class="row share">
				<div class="row tag-follow" style="margin-top: 40px;margin-left: 12px;width: 220px;">
					<%
						if (uib != null) {
					%>
				<!-- ask-->
				<button type="button" class="btn btn-primary btn-lg btn-block  ask"
					onclick="location='getAllTagsInfo'">我要提问</button>
				<!-- ask-->
				<%}else { %>
				<!-- ask-->
				<button type="button" class="btn btn-primary btn-lg btn-block  ask"
					onclick="location='userLogin.jsp'">我要提问</button>
				<!-- ask-->
				<% } %>
				</div>
				<br><br>
				</div>
				<!-- end of follow -->

				<!-- hot tag-->
				<div class="row">
					<div class="col-lg-8">
						<h4>热门标签</h4>
					</div>
					<div class="col-lg-4">
						<p></p>
						<p class="more">
							<a href="#">更多</a>
						</p>
					</div>
					<div class="col-md-12">
						<c:forEach items="${listTags }" var="tag" begin="0" step="1"
							varStatus="class">
							<span class="mark-p label label-default">${tag.tags_name }</span>
						</c:forEach>
					</div>
				</div>
				<!-- end of hot tag -->
				<br><br>
				<!-- share -->
				<div class="row share">
					<div class="col-lg-12">
						<!-- JiaThis Button BEGIN -->
						<div id="ckepop">
							<span class="jiathis_txt">分享到：</span> <a
								class="jiathis_button_tsina">新浪微博</a> <a
								href="http://www.jiathis.com/share"
								class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis"
								target="_blank">更多</a> <a class="jiathis_counter_style"></a>
						</div>
						<script type="text/javascript"
							src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
						<!-- JiaThis Button END -->
					</div>
				</div>

			</div>
			<!-- right-->

		</div>
	</div>
	<!-- main-content end-->
	<hr class="hr-header">
	<script type="text/javascript">
      $('#myTab li').click(function (e) {
        e.preventDefault()
          $(this).tab('show')
        })
    </script>
	<!--foot-->
	<div class="foot">
		<div class="container">
			<p></p>
			<p>Power by nxhnust Current version 0.0.1</p>
		</div>
	</div>
	<!--foot-->
</body>
</html>
