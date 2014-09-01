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
<meta property="qc:admins" content="743421445534116451006375" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>智能问答主页——科大专业的问答平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="湖南科技大学,问答平台,智能,湖科大,湖南科大">
<meta http-equiv="description" content="湖南科技大学只能问答平台">
<!-- Bootstrap core CSS -->
<link href="./dist/css/bootstrap.css" rel="stylesheet">
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="static/style/custom.css">

<script src="js/jquery.min.js"></script>
<script src="./dist/js/bootstrap.js"></script>
<script type="text/javascript">
		
		$('#myTab a').click(function (e) {
	        e.preventDefault();
	        $(this).tab('show');
	    });
		
	</script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="static/style/custom.css" type="text/css"></link>
</head>
<script type="text/javascript" src="js/question/index.js"></script>
<script type="text/javascript" src="js/question/follow.js"></script>
<body>
	<!-- 
  	休得无理，看源码者请绕道，哈哈~。
  		—————小飞机笔QQ:707406343，欢迎交流！
   -->
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
                <li><a href="#">消息 <span class="badge">0</span></a></li>
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
				<div class="bs-example bs-example-tabs">
					<ul id="myTab" class="nav nav-tabs nav-right">
						<%--<li class=""><a href="getHotQuestions" data-toggle="tab">Hot</a></li>--%>
						<li class=""><a href="#hot" data-toggle="tab">热门提问</a></li>
						<li class="active"><a href="#new" data-toggle="tab">最新问题
								&nbsp;</a></li>
					</ul>
					<div id="myTabContent" class="tab-content">


						<div class="tab-pane fade active in" id="new">
							<ul class="qa-list">

								<!--  content list -->
								<c:forEach items="${listQuestions }" var="question" begin="0"
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
											 <a href="/GetPersionInfoServlet?user_id=${question.questionBean.question_user_id }"><img src="images/git.png" alt="..." class="qa-img img-circle"></a>
										</div>
									</li>
									<!--  content list end-->
								</c:forEach>
							</ul>
							<div style="text-align: center;">

								<c:choose>
									<c:when test="${currentPage == 0 }">首页&nbsp;上一页&nbsp;</c:when>
									<c:otherwise>
										<a href="disscussQuestion?currentPage=0">首页</a>&nbsp;<a
											href="disscussQuestion?currentPage=${currentPage - 1}">上一页</a>&nbsp;</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${currentPage >=  len  }">下一页&nbsp;末页&nbsp;</c:when>
									<c:otherwise>
										<a href="disscussQuestion?currentPage=${currentPage + 1 }">下一页</a>&nbsp;<a
											href="disscussQuestion?currentPage=-1">末页</a>&nbsp;</c:otherwise>
								</c:choose>

							</div>

						</div>

						<div class="tab-pane fade" id="hot">

							<ul class="qa-list">

								<!--  content list -->

								<c:forEach items="${listHotQuestions }" var="question" begin="0"
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
											 <a href="/GetPersionInfoServlet?user_id=${question.questionBean.question_user_id }"><img src="images/git.png" alt="..." class="qa-img img-circle"></a>
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

				<!-- person-->
				<div class="row fix-margin-top">

					<div class="col-md-7">
						<img src="images/logo.png" alt="" class="head-img img-thumbnail">
					</div>

					<div class="col-md-5 follow">
						<a type="button" class="btn btn-default"> <span
							class="glyphicon glyphicon-star"></span>问 &nbsp;&nbsp;&nbsp;题
							${totalOfQuestion }个
						</a> <a type="button" class="btn btn-default"> <span
							class="glyphicon glyphicon-user"></span>已解决 ${countOfSolution }个
						</a> <a type="button" class="btn btn-default"> <span
							class="glyphicon glyphicon-bookmark"></span>用&nbsp;&nbsp;&nbsp;户
							${totalOfUser }个
						</a>
					</div>
				</div>
				<!-- person -->

				<!-- person edit-->
				<div class="row fix-margin-top">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a href="#" class="panel-title pull-right border-0"></a>
								<h3 class="panel-title">系统公告</h3>
							</div>
							<div class="panel-body"
								style="font-size: 14px; line-height: 25px; font-style: inherit; padding: 0px;">
								<ul>
									<li>赞，踩功能及人人网官方认证主页已上线；</li>
									<li>问题搜索优化：基于问题的多分词匹配搜索；</li>
									<li>用户的注册成功邮箱提醒和邮箱验证密码找回;</li>
									<li>基于问题词频统计进行问题推荐的功能。</li>
								</ul>
							</div>
							<div
								style="text-align: right; background-color: white; margin-bottom: 5px;">
								<font color=" #3276b1;">2014年8月14日<br>——湖南科技大学智能问答平台团队
								</font>
							</div>
						</div>
					</div>
				</div>
				<!-- person edit-->

				<!-- mark-->
				<div class="row fix-margin-top">
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
				</div>
				<!-- person edit-->

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
				<!-- mark -->
				<!-- 风云人物 -->
				<div class="row fix-margin-top">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a href="#" class="panel-title pull-right border-0"></a>
								<h3 class="panel-title">风云人物</h3>
							</div>
							<div class="panel-body" style="padding: 0px;">
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
														<c:forEach items="${listHotUser }" var="user" begin="0" step="1"
													varStatus="class" end="0"> 
														<a href="/GetPersionInfoServlet?user_id=${user.userInfo.user_id }">${user.userInfo.user_name }</a><br> <a href="#">积分：${user.userInfo.user_mark }</a> <br>
														<a href="/GetPersionInfoServlet?user_id=${user.userInfo.user_id }">回答问题数：${user.ansTotal }</a>
														</c:forEach>
													</p>
												</div>

											</div>
										</li>
										<c:forEach items="${listHotUser }" var="user" begin="1" step="1"
										varStatus="class">
										<li><span class="col-md-6"><a href="/GetPersionInfoServlet?user_id=${user.userInfo.user_id }"
												target="_blank">${user.userInfo.user_name }</a></span><span class="col-md-6">回答问题数：${user.ansTotal }</span></li>
									</c:forEach>
									</ul>
									
								</div>
							</div>
						</div>
					</div>
					<!-- 风云人物 -->
						<div class="col-md-12" style="padding-left: 15px;">
					<iframe scrolling="no" frameborder="0"
						src="http://www.connect.renren.com/widget/liveWidget?api_key=a9f2352f864843419ea40ea8ed5316e7&xid=default&url=http%3A%2F%2Fhnustqa.duapp.com&desp=%E5%A4%A7%E5%AE%B6%E6%9D%A5%E8%AE%A8%E8%AE%BA"
						style="width: 261px; height: 300px;"></iframe>
						</div>
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