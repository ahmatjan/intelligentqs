<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>第三方账号绑定</title>
    <!-- Bootstrap core CSS -->
    <link href="./dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./static/style/signin.css" rel="stylesheet">
	<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon">
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
          <div class='logo'></div>
          <div class='decription'></div>
       -->
        </div>

        <div class="col-md-6">
            <form class="form-signin" role="form" name="userRegister" id="userRegister" action="registerServlet" method="post">
              <h2 class="form-signin-heading"><font color="white">第三方账号绑定${userName }</h2>
              <input type="text" id="user" name="userName" class="form-control" placeholder="User Name" required autofocus value="${userName }" readonly="readonly">
               <input type="hidden" id="passwd1" name="passwd1" class="form-control" placeholder="Password" required  value="${userPassword }">
               <input type="hidden" id="passwd1" name="passwd2" class="form-control" placeholder="Password" required  value="${userPassword }">
               <input type="text" id="user" name="userEmail" class="form-control" placeholder="Email将用来找回密码" required autofocus>
              <button class="btn btn-lg btn-primary btn-block" type="submit">绑定</button>
             <a style="color: red;margin-left:180px;text-decoration: none" >${Msg }</a>
			</form>
          <div class='contains'></div>
        </div>
        </div>
      </div>
<!-- foot-->
	<div  style="width: 100%;text-align: center;padding-top: 100px;">
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