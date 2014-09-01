<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>腾讯微博登录组件</title>
<script src="http://mat1.gtimg.com/app/openjs/openjs.js"></script>
<link rel="stylesheet" type="text/css" href="css/tencentweibo.css">
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon">
</head>
<body>
	<div class="qq_account_log" id="qq_account_log">
		<a class="login_btn login_btn03" id="login_btn" href="javascript:;">
		</a>
		<div class="logout_btn" id="logout_btn"></div>
	</div>
	<script defer="defer">
		var login_btn = document.getElementById("login_btn"), logout_btn = document
				.getElementById("logout_btn");
		function login() {
			T.login(function(loginStatus) {
				getUserInfo();
				login_btn.style.display = "none"
				logout_btn.style.display = "inline-block";
			}, function(loginError) {
				alert(loginError.message);
			});
		}
		function logout() {
			logout_btn.style.display = "none";
			login_btn.style.display = "inline-block";
			T.logout();
		}
		function getUserInfo() {
			T
					.api("/user/info")
					.success(
							function(response) {
								if (response.ret === 0) {
									var html = "", imgsrc = "", data = response.data;
									html = data.name + data.nick
											+ '<a class="logout_text" id="logout_text"　href="javascript:void(0);">logout</a></span>';
									logout_btn.innerHTML = html;
									var logout_text = document
											.getElementById("logout_text");
									logout_text.onclick = logout;
								} else {
									alert(response.ret);
								}
							}).error(function(code, message) {
						alert(message);
					});
		}
		function init() {
			T.init({
				appkey : 801527196
				
			});
			if (!T.loginStatus()) {
				login_btn.style.display = "inline-block";
				logout_btn.style.display = "none";
			} else {
				getUserInfo();
				login_btn.style.display = "none";
				logout_btn.style.display = "inline-block";
			}
			login_btn.onclick = login;
		}
		init();
	</script>
</body>
</html>