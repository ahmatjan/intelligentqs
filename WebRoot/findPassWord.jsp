<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>用户找回密码</title>
<script type="text/javascript">
//刷新验证码
	function refresh()
	{
		document.getElementById("authImg").src="authImg?now="+new Date();//使用时间作为参数避免浏览器从缓存取图片
	}
</script>
<!-- Bootstrap core CSS -->
<link href="./dist/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./static/style/signin.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="col-md-6">
				<!-- 小飞机无能为力了，看源码者勿扰
       -->
			</div>

			<div class="col-md-6">
				<form class="form-signin" role="form" name="userGetPassword"
					id="userGetPassword" action="getPassword" method="post">
					<h2 class="form-signin-heading" align="center" style="color: white;">找回密码</h2>
					<input type="text" name="user_name" id="user_name"
						class="form-control" placeholder="用户名" required autofocus value="${user_name }">
					<input type="text" name="user_email" id="user_name"
						class="form-control" placeholder="注册时的邮箱" required value="${user_email }"> <input
						type="text" class="form-control" id="inputEmail3"
						placeholder="验证码" name="vercode"> <img alt=""
						src="authImg" mce_src="authImg" id="authImg" align="absmiddle"
						onclick="refresh()"><a href="#" onclick="refresh();"><span
						style="font-size: 15px; margin-left: 50px;"
						mce_style="font-size:12px;c"><font color="white">刷新验证码</font></span>
					</a> 
					<button class="btn btn-lg btn-primary btn-block" type="submit">找回密码</button>
						<font color="white">请务必填写正确的用户名和和注册时邮箱，我们将发送一封邮件到您注册时的邮箱，注意查收！如果您没有收到，请确保是否被系统放到垃圾邮箱或则可以联系我们，我们会在收到申请的3个工作日内处理：
						<a href="mailto:707406343@qq.com">707406343@qq.com</a></font> <a
						style="color: red; margin-left: 100px; text-decoration: none">${Msg }</a>
				</form>
				<div class='contains'></div>
			</div>
		</div>
	</div>
	<!-- foot-->
	<div  style="width: 100%;text-align: center;padding-top: 150px;">
		<div class="container">
			<p></p>
			<p style="color: #eee;">Power by hnust_qa current version 1.0</p>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
