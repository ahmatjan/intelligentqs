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
<title>用户登录</title>
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

<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
      $("button").click(function(){
        
        var user = $("input#user").val();
        var first = $("input#passwd1").val();
        var second = $("input#passwd2").val();
        
        if ( first === second ){
         
          $.post("#",
            {
              'username' : user,
              'password' : first
            },
            function(data){
            }
          );
        }
      });
    });
    </script>

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
				<form class="form-signin" role="form" name="userLogin"
					id="userLogin" action="loginServlet" method="post">
					<h2 class="form-signin-heading">登录</h2>
					<input type="text" name="user_name" id="user_name"
						class="form-control" placeholder="UserName" required autofocus>
					<input type="password" name="user_password" id="user_password"
						class="form-control" placeholder="Password" required> <input
						type="text" class="form-control" id="inputEmail3"
						placeholder="Auth-code" name="vercode"> <img alt=""
						src="authImg" mce_src="authImg" id="authImg" align="absmiddle"
						onclick="refresh()"><a href="#" onclick="refresh();"><span
						style="font-size: 15px; margin-left: 50px;"
						mce_style="font-size:12px;c"><font color="white">刷新验证码</font></span>
					</a> <label class="checkbox"><input type="checkbox"
						value="remember-me" checked="checked"> 记住我
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="findPassWord.jsp" style="color: red;">忘记密码?</a></label>
					<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
 					<br>
 					<label class='loginlabel'><a href='userRegister.jsp'><font
							color="white">点击注册</font></a><font color='white'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人人登录：&nbsp;&nbsp;<a href="https://graph.renren.com/oauth/authorize?client_id=
             ${appId}&response_type=code&redirect_uri=${redirectUri}&display=page"><img style=
                    "border:0px" /><img alt="人人账号登陆" src="images/renren3.png" height="22px"></a></font></label><a
						style="color: red; margin-left: 100px; text-decoration: none">${Msg }</a>
						
				</form>
				<div class='contains'></div>
			</div>
		</div>
	</div>
	<!-- foot-->
	<div  style="width: 100%;text-align: center;padding-top: 75px;">
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
 					