<%@page import="cn.com.daos.DiscussDaoImp"%>
<%@page import="cn.com.daos.UserInfoDaoImp"%>
<%@page import="cn.com.beans.DiscussAllInfoBean"%>
<%@page import="cn.com.beans.DiscussBean"%>
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
	UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<script src="js/jquery-1.7.1.js"></script>
<script type='text/javascript'>
	$(document).ready(function() {
		$(".flip").click(function() {

			$(".panel").slideToggle("slow");
		});
	});
</script>
<script src="js/question/praise.js" type='text/javascript'></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>用户${question.questionUserName }在湖南科技大学智能问答平台上提问：${question.questionBean.question_title }</title>
<!-- Bootstrap core CSS -->
<link href="dist/css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="static/style/custom.css">
<link rel="stylesheet" type="text/css" href="tatic/style/wy.css">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">

<script src=".static/style/wy.js"></script>
<script src="dist/js/bootstrap.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</style>
</head>
<script
	src="http://mat1.gtimg.com/app/openjs/openjs.js#autoboot=no&debug=no"></script>
<body>
	<div class="container header-bg">
		<nav class="navbar navbar-default header-border header-height"
			role="navigation">

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div class="row">

					<div class="col-lg-6">
						<form class="navbar-form navbar-left" role="search"
							action="searchQuestion" method="post">
							<div class="input-group ">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit">Search</button>
								</span> <input type="text" class="form-control" name="context">
							</div>
							<!-- /input-group -->
						</form>
					</div>
					<!-- /.col-lg-6 -->

					<div class="col-lg-6">

						<%
							if (uib != null) {
						%>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="index">首页</a> <!-- 
						<li><div class="col-md-2"><img src="<%=uib.getUser_logo()%>" alt="" style="width:36px;height: 42px;padding-top: 8px"></div></li>
						 -->
							<li><a href="GetPersionInfoServlet"><%=uib.getUser_name()%></a>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Account <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="GetPersionInfoServlet">我的主页</a></li>
									<li><a href="userLogOffServlet">退出</a></li>
									<li class="divider"></li>
									<li><a href="#">建议反馈</a></li>
								</ul></li>
						</ul>
						<%
							} else {
						%>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="index">首页</a>
							<li><a href="userLogin.jsp">登录</a></li>
							<li><a href="userRegister.jsp">注册</a></li>
						</ul>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</div>

	<hr class="hr-header">



	<div class="container main-content">

		<!--question title start-->
		<div class="row">
			<div class="col-lg-12">
				<h2>
					<small id='questionid' value="${question.questionBean.question_id }">${question.questionBean.question_title }</small>
				</h2>
			</div>
		</div>
		<!--question title end-->

		<hr class="hr-header">
		<!--Contents-->
		<div class="row">
			<!-- left-->
			<div class="col-lg-9">
				<div class="question">
					<div class="row">
						<!--start-->
						<div class="col-lg-1">
							<button type="button" class="btn btn-primary btn-block btn-lg">
								<span id="praises"></span>
							</button>
							<button type="button" class="btn btn-default btn-block btn-sm" id="praise">
								<span class=" glyphicon glyphicon-thumbs-up" id="praise"></span>赞
							</button>

							<button type="button" class="btn btn-default btn-block btn-sm" id="tread">
								<span class=" glyphicon glyphicon-thumbs-down"></span>踩
							</button>
							<button type="button" class="btn btn-default btn-block btn-sm">
								<span class="glyphicon glyphicon-star"></span>收藏
							</button>
						</div>
						<!--end-->
						<!--start-->
						<div class="col-lg-11">
							<div class="question-contents">
								${fn:escapeXml(question.questionBean.question_description )}
								<div class="row">
									<div class="col-md-7">
										<div class="qa-tags">
											<p></p>
											<span class="glyphicon glyphicon-tags"> </span> <a href="#">
												${question.questionBean.question_tags }</a>
										</div>
									</div>
									<div class="col-md-1">
										<img src="images/git.png" alt="..." class="qa-img img-circle">
									</div>
									<div class="col-md-2">
										<p>${question.questionUserName }</p>
										<p>${question.questionBean.question_time }提问</p>
									</div>
									<div class="col-lg-12">
										<hr class="hr-comment">
									</div>
								</div>
							</div>
							<!--end of question-contents-->

						</div>
						<!-- end of list-3 -->
					</div>
					<p style="background-color: white;">&nbsp;</p>
				</div>
				<!--end of answer-->

				<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-11">
						<ul id="myTab" class="row nav nav-tabs nav-right">
							<li href="#time" class=""><a href="#" data-toggle="tab">得票数</a>
							</li>
							<li href="#vote" class=""><a href="#" data-toggle="tab">时间先后</a>
							</li>
							<li class="col-lg-9 col-md-offset--3">
								<h3 class="">${coutAnswer }个回答</h3>
								<p></p>
								<p></p>
							</li>
						</ul>
						<script type="text/javascript">
							$('#myTab li').click(function(e) {
								e.preventDefault()
								$(this).tab('show')
							})
						</script>
					</div>
				</div>
				<c:forEach items="${listAnswer }" var="answer" begin="0" step="1"
					varStatus="class">
					<div class="row list-1">
						<!--start-->
						<div class="col-lg-1">
							<button type="button" class="btn btn-primary btn-block btn-lg">
								<span>0</span>
							</button>
							<button type="button" class="btn btn-default btn-block btn-sm">
								<span class=" glyphicon glyphicon-thumbs-up"></span>赞
							</button>
							<button type="button" class="btn btn-default btn-block btn-sm">
								<span class=" glyphicon glyphicon-thumbs-down"></span>踩
							</button>
						</div>
						<!--end-->

						<!--start-->
						<div class="col-lg-11">
							<p></p>
							<ul>
								<li>${fn:escapeXml(answer.answerBean.answer_description) }</li>
							</ul>
							<p></p>
							<div class="row">
								<div class="col-md-9"></div>
								<div class="col-md-1">
									<img src="./static/image/git.png" alt="..."
										class="qa-img img-circle">
								</div>
								<div class="col-md-2">
									<p>用户&nbsp;${answer.userName  }</p>
									<p>${answer.answerBean.answer_time}回答</p>
								</div>
								<div class="col-lg-12">
									<hr class="hr-comment">
								</div>
							</div>
							<div style="text-align: left;">
								<p class="flip">
									<font color="green" size="4">查看评论</font>
								</p>${msg }
							</div>

							<div class="panel" style="background-color: #eee; display: none;">
								<form action="AddDiscussServlet" method="post">
									<input type="hidden" name="answer_id"
										value="${ answer.answerBean.answer_id}"> <input
										type="hidden" name="question_id"
										value="${question.questionBean.question_id }"> <input
										class="form-control" type="text" placeholder="对此问题有疑问？"
										name="discuss_content" id="submitcomment"> <input
										type="submit" class="btn btn-default" />
								</form>
								<c:forEach items="${listAllDiscuss }" var="listDiscuss"
									begin="0" step="1" varStatus="class">
									<%-- 评论ID：${ listDiscuss.discussBean.answer_id }
									答案ID：${ answer.answerBean.answer_id}<br>
							 		${listDiscuss.userName }
									${listDiscuss.discussBean.content }   --%>
									<!-- 比较答案ID和评论中的问题ID一致者显示 -->
									<c:if
										test="${ listDiscuss.discussBean.answer_id == answer.answerBean.answer_id }">


										<div class="comments">
											<div class="comment-list">

												<div class="row">
													<div class="col-lg-1">
														<img src="./static/image/git.png" alt="..."
															class="qa-img img-circle">
													</div>
													<div class="col-lg-10">
														<p>
															<a href="#"><span>${listDiscuss.userName }</span></a> <span>${listDiscuss.discussBean.time  }评论：</span>
														</p>
														<p>${listDiscuss.discussBean.content }</p>
													</div>
													<div class="col-lg-1 comment-reply">
														<p>&nbsp;</p>
													</div>
													<div class="col-lg-12">
														<hr style="border-color: gray;">
													</div>
												</div>
											</div>
											<!--end of comment-list-->
										</div>
									</c:if>
								</c:forEach>
							</div>
							<hr class="hr-header">
						</div>
						<!--end-->
					</div>
				</c:forEach>
				<form action="addAnswerInfo" method="post">
					<div class="edit-answer">
						<div class="row">
							<!--start of editor-->

							<div class="col-lg-12 fix-margin-top" style="">
								<h3>撰写答案</h3>

								<div class="btn-toolbar" data-role="editor-toolbar"
									data-target="#editor">
									<div class="btn-group">
										<a class="btn dropdown-toggle" data-toggle="dropdown" title=""
											data-original-title="Font"> <i class="icon-font"></i> <b
											class="caret"></b>
										</a>
										<ul class="dropdown-menu">
											<li><a data-edit="fontName Serif"
												style="font-family: 'Serif'">Serif</a></li>
											<li><a data-edit="fontName Sans"
												style="font-family: 'Sans'">Sans</a></li>
											<li><a data-edit="fontName Arial"
												style="font-family: 'Arial'">Arial</a></li>
											<li><a data-edit="fontName Arial Black"
												style="font-family: 'Arial Black'">Arial Black</a></li>
											<li><a data-edit="fontName Courier"
												style="font-family: 'Courier'">Courier</a></li>
											<li><a data-edit="fontName Courier New"
												style="font-family: 'Courier New'">Courier New</a></li>
											<li><a data-edit="fontName Comic Sans MS"
												style="font-family: 'Comic Sans MS'">Comic Sans MS</a></li>
											<li><a data-edit="fontName Helvetica"
												style="font-family: 'Helvetica'">Helvetica</a></li>
											<li><a data-edit="fontName Impact"
												style="font-family: 'Impact'">Impact</a></li>
											<li><a data-edit="fontName Lucida Grande"
												style="font-family: 'Lucida Grande'">Lucida Grande</a></li>
											<li><a data-edit="fontName Lucida Sans"
												style="font-family: 'Lucida Sans'">Lucida Sans</a></li>
											<li><a data-edit="fontName Tahoma"
												style="font-family: 'Tahoma'">Tahoma</a></li>
											<li><a data-edit="fontName Times"
												style="font-family: 'Times'">Times</a></li>
											<li><a data-edit="fontName Times New Roman"
												style="font-family: 'Times New Roman'">Times New Roman</a></li>
											<li><a data-edit="fontName Verdana"
												style="font-family: 'Verdana'">Verdana</a></li>
										</ul>
									</div>
									<div class="btn-group">
										<a class="btn dropdown-toggle" data-toggle="dropdown" title=""
											data-original-title="Font Size"> <i
											class="icon-text-height"></i>&nbsp; <b class="caret"></b>
										</a>
										<ul class="dropdown-menu">
											<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
											<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
											<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
										</ul>
									</div>
									<div class="btn-group">
										<a class="btn" data-edit="bold" title=""
											data-original-title="Bold (Ctrl/Cmd+B)"><i
											class="icon-bold"></i></a> <a class="btn" data-edit="italic"
											title="" data-original-title="Italic (Ctrl/Cmd+I)"><i
											class="icon-italic"></i></a> <a class="btn"
											data-edit="strikethrough" title=""
											data-original-title="Strikethrough"><i
											class="icon-strikethrough"></i></a> <a class="btn"
											data-edit="underline" title=""
											data-original-title="Underline (Ctrl/Cmd+U)"><i
											class="icon-underline"></i></a>
									</div>
									<div class="btn-group">
										<a class="btn" data-edit="insertunorderedlist" title=""
											data-original-title="Bullet list"><i class="icon-list-ul"></i></a>
										<a class="btn" data-edit="insertorderedlist" title=""
											data-original-title="Number list"><i class="icon-list-ol"></i></a>
										<a class="btn" data-edit="outdent" title=""
											data-original-title="Reduce indent (Shift+Tab)"><i
											class="icon-indent-left"></i></a> <a class="btn"
											data-edit="indent" title=""
											data-original-title="Indent (Tab)"><i
											class="icon-indent-right"></i></a>
									</div>
									<div class="btn-group">
										<a class="btn" data-edit="justifyleft" title=""
											data-original-title="Align Left (Ctrl/Cmd+L)"><i
											class="icon-align-left"></i></a> <a class="btn"
											data-edit="justifycenter" title=""
											data-original-title="Center (Ctrl/Cmd+E)"><i
											class="icon-align-center"></i></a> <a class="btn btn-info"
											data-edit="justifyright" title=""
											data-original-title="Align Right (Ctrl/Cmd+R)"><i
											class="icon-align-right"></i></a> <a class="btn"
											data-edit="justifyfull" title=""
											data-original-title="Justify (Ctrl/Cmd+J)"><i
											class="icon-align-justify"></i></a>
									</div>
									<div class="btn-group">
										<a class="btn dropdown-toggle" data-toggle="dropdown" title=""
											data-original-title="Hyperlink"><i class="icon-link"></i></a>
										<div class="dropdown-menu input-append">
											<input class="span2" placeholder="URL" type="text"
												data-edit="createLink">
											<button class="btn" type="button">Add</button>
										</div>
										<a class="btn" data-edit="unlink" title=""
											data-original-title="Remove Hyperlink"><i
											class="icon-cut"></i></a>
									</div>
									<div class="btn-group">
										<a class="btn" title="" id="pictureBtn"
											data-original-title="Insert picture (or just drag &amp; drop)"><i
											class="icon-picture"></i></a> <input type="file"
											data-role="magic-overlay" data-target="#pictureBtn"
											data-edit="insertImage"
											style="opacity: 0; position: absolute; top: 0px; left: 0px; width: 41px; height: 30px;">
									</div>
									<div class="btn-group">
										<a class="btn" data-edit="undo" title=""
											data-original-title="Undo (Ctrl/Cmd+Z)"><i
											class="icon-undo"></i></a> <a class="btn" data-edit="redo"
											title="" data-original-title="Redo (Ctrl/Cmd+Y)"><i
											class="icon-repeat"></i></a>
									</div>
									<input type="hidden" name="question_id"
										value="${question.questionBean.question_id }"> <input
										type="text" data-edit="inserttext" id="voiceBtn"
										x-webkit-speech="" style="display: none;">
								</div>
								<!-- <div id="editor" contenteditable="true">123...</div> -->
								<div class="">
									<textarea id="editor"
										class="form-control mono textarea-14 mousetrap"
										placeholder="为此问题提供一个答案" rows="8" autocomplete="off"
										spellcheck="false" name="answer_description"></textarea>
								</div>

							</div>
							<!--end of editor-->
							<div class="col-lg-10"></div>
							<div class="col-lg-1">
								<p></p>
								<input type="submit" class="btn btn-primary btn-xl" value="提交答案">
							</div>
						</div>
					</div>
					<!--end of edit-answer-->
				</form>
			</div>
			<!-- left-->

			<!-- right-->
			<div class="col-lg-3">

				<!--follow-->
				<div class="row">
					<div class="col-lg-12">
						<%
							if (uib != null) {
						%>
						<!-- ask-->
						<button type="button"
							class="btn btn-primary btn-lg btn-block  ask"
							onclick="location='getAllTagsInfo'">我要提问</button>
						<!-- ask-->
						<%
							} else {
						%>
						<!-- ask-->
						<button type="button"
							class="btn btn-primary btn-lg btn-block  ask"
							onclick="location='userLogin.jsp'">登录后提问</button>
						<!-- ask-->
						<%
							}
						%>
					</div>
					<div class="col-lg-12">
						<p></p>
						<p class="text-left">
							共 <a href="#">840</a> 人关注该问题
						</p>
						<hr class="hr-aside">
					</div>
				</div>
				<!-- end of follow -->

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
				<hr class="hr-aside">
				<!-- similar-question -->
				<div class="row similar-question">
					<div class="col-lg-12">
						<br>
						<h4>相关问题</h4>
					</div>
					<div class="col-lg-12 related-list">
						<ul class="list-unstyled">
									<c:forEach items="${questionKeyList }" var="questionKey" begin="0" step="1"
											varStatus="class" end="20">
												<li><a target="_self" href="getDetilQuestion?question_id=${questionKey.question_id }" id="${questionKey.question_id }">${questionKey.question_title } ...${questionKey.question_time}</a><br><br>
										<%-- 			<p>${fn:substring(questionKey.question_description ,0,10)}...${questionKey.question_time}</p> --%>
												</li>
									</c:forEach>
						</ul>
						<hr class="hr-aside">
					</div>
				</div>
				<!-- end of similar-question -->
			</div>
			<!-- right-->

		</div>
	</div>
	<!-- main-content end-->
	<hr class="hr-header">
	<script>
		$(function() {
			function initToolbarBootstrapBindings() {
				var fonts = [ 'Serif', 'Sans', 'Arial', 'Arial Black',
						'Courier', 'Courier New', 'Comic Sans MS', 'Helvetica',
						'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma',
						'Times', 'Times New Roman', 'Verdana' ], fontTarget = $(
						'[title=Font]').siblings('.dropdown-menu');
				$
						.each(
								fonts,
								function(idx, fontName) {
									fontTarget
											.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'
													+ fontName + '</a></li>'));
								});
				$('a[title]').tooltip({
					container : 'body'
				});
				$('.dropdown-menu input').click(function() {
					return false;
				}).change(
						function() {
							$(this).parent('.dropdown-menu').siblings(
									'.dropdown-toggle').dropdown('toggle');
						}).keydown('esc', function() {
					this.value = '';
					$(this).change();
				});

				$('[data-role=magic-overlay]').each(
						function() {
							var overlay = $(this), target = $(overlay
									.data('target'));
							overlay.css('opacity', 0).css('position',
									'absolute').offset(target.offset()).width(
									target.outerWidth()).height(
									target.outerHeight());
						});
				$('#voiceBtn').hide();
				// if ("onwebkitspeechchange"  in document.createElement("input")) {
				//   var editorOffset = $('#editor').offset();
				//   $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
				// } else {
				//   $('#voiceBtn').hide();
				// }
			}
			;
			initToolbarBootstrapBindings();
			$('#editor').wysiwyg();
			window.prettyPrint && prettyPrint();
		});
	</script>
	<!-- foot-->
	<div class="foot">
		<div class="container">
			<p></p>
			<p>Power by hnust_qa current version 1.0</p>
		</div>
	</div>

</body>
</html>
