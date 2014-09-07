<%@page import="cn.com.beans.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>提问</title>
    <!-- Bootstrap core CSS -->
    <link href="./dist/css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="./static/style/custom.css">
    <link rel="stylesheet" type="text/css" href="./static/style/wy.css">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/logo.ico" type="image/x-icon">
    <script src="./static/style/wy.js"></script> 
   <!-- <script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script> -->
    <script src="js/jquery-1.7.1.js"></script>
    <script src="./dist/js/bootstrap.js"></script>
    <script src="js/question/simple-taginput.js"></script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//HttpSession session =  (HttpSession)request.getSession(); 
	UserInfoBean uib = (UserInfoBean) session
			.getAttribute("userBean");
%>
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
      
        <!--Contents-->
        <div class="row">

 <form action="askQuestionServlet" method="post" name="form" id="form">
    <!-- left-->
            <div class="col-lg-9">
              <div class="row">
                <div class="col-lg-12">
                  <h2><small>提出新问题<font color="red">${ Msg}</font></small></h2>
                </div>
                <hr class="hr-header">
              <!--question title start-->
                <div class="col-lg-12">
                  <div class="input-group input-group-lg">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">标题</button>
                    </span>
                    <input type="text" class="form-control" placeholder="用一句话说清楚你的问题" name="question_title">
                  </div>
                </div>
              <!--question title end-->

              <!--start of editor-->   
                <div class="col-lg-12">
                  <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
                      <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Font">
                          <i class="icon-font"></i>
                          <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                          <li><a data-edit="fontName Serif" style="font-family:'Serif'">Serif</a></li>
                          <li><a data-edit="fontName Sans" style="font-family:'Sans'">Sans</a></li>
                          <li><a data-edit="fontName Arial" style="font-family:'Arial'">Arial</a></li>
                          <li><a data-edit="fontName Arial Black" style="font-family:'Arial Black'">Arial Black</a></li>
                          <li><a data-edit="fontName Courier" style="font-family:'Courier'">Courier</a></li>
                          <li><a data-edit="fontName Courier New" style="font-family:'Courier New'">Courier New</a></li>
                          <li><a data-edit="fontName Comic Sans MS" style="font-family:'Comic Sans MS'">Comic Sans MS</a></li>
                          <li><a data-edit="fontName Helvetica" style="font-family:'Helvetica'">Helvetica</a></li>
                          <li><a data-edit="fontName Impact" style="font-family:'Impact'">Impact</a></li>
                          <li><a data-edit="fontName Lucida Grande" style="font-family:'Lucida Grande'">Lucida Grande</a></li>
                          <li><a data-edit="fontName Lucida Sans" style="font-family:'Lucida Sans'">Lucida Sans</a></li>
                          <li><a data-edit="fontName Tahoma" style="font-family:'Tahoma'">Tahoma</a></li>
                          <li><a data-edit="fontName Times" style="font-family:'Times'">Times</a></li>
                          <li><a data-edit="fontName Times New Roman" style="font-family:'Times New Roman'">Times New Roman</a></li>
                          <li><a data-edit="fontName Verdana" style="font-family:'Verdana'">Verdana</a></li>
                        </ul>
                      </div>
                      <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Font Size">
                          <i class="icon-text-height"></i>&nbsp;
                          <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                          <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
                          <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
                          <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
                        </ul>
                      </div>
                      <div class="btn-group">
                        <a class="btn" data-edit="bold" title="" data-original-title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
                        <a class="btn" data-edit="italic" title="" data-original-title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
                        <a class="btn" data-edit="strikethrough" title="" data-original-title="Strikethrough"><i class="icon-strikethrough"></i></a>
                        <a class="btn" data-edit="underline" title="" data-original-title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
                      </div>
                      <div class="btn-group">
                        <a class="btn" data-edit="insertunorderedlist" title="" data-original-title="Bullet list"><i class="icon-list-ul"></i></a>
                        <a class="btn" data-edit="insertorderedlist" title="" data-original-title="Number list"><i class="icon-list-ol"></i></a>
                        <a class="btn" data-edit="outdent" title="" data-original-title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
                        <a class="btn" data-edit="indent" title="" data-original-title="Indent (Tab)"><i class="icon-indent-right"></i></a>
                      </div>
                      <div class="btn-group">
                        <a class="btn" data-edit="justifyleft" title="" data-original-title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
                        <a class="btn" data-edit="justifycenter" title="" data-original-title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>
                        <a class="btn btn-info" data-edit="justifyright" title="" data-original-title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
                        <a class="btn" data-edit="justifyfull" title="" data-original-title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>
                      </div>
                      <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" title="" data-original-title="Hyperlink"><i class="icon-link"></i></a>
                        <div class="dropdown-menu input-append">
                          <input class="span2" placeholder="URL" type="text" data-edit="createLink">
                          <button class="btn" type="button">Add</button>
                        </div>
                        <a class="btn" data-edit="unlink" title="" data-original-title="Remove Hyperlink"><i class="icon-cut"></i></a>
                      </div>
                      <div class="btn-group">
                        <a class="btn" title="" id="pictureBtn" data-original-title="Insert picture (or just drag &amp; drop)"><i class="icon-picture"></i></a>
                        <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" style="opacity: 0; position: absolute; top: 0px; left: 0px; width: 41px; height: 30px;">
                      </div>
                      <div class="btn-group">
                        <a class="btn" data-edit="undo" title="" data-original-title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
                        <a class="btn" data-edit="redo" title="" data-original-title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
                      </div>
                      <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="" style="display: none;">
                  </div>
                </div>
                <div class="col-lg-12">
                  <textarea id="editor" class="form-control textarea-14 mousetrap" placeholder="为此问题提供一些描述一便更好的解答..." rows="8" autocomplete="off" spellcheck="false" name="question_description"></textarea>
                </div>
              <!--end of editor-->
              </div>
              
              
            </div><!-- end of col-lg-9 -->
    <!-- left-->

    <!-- right-->
            <div class="col-lg-3">
                  <div class="list-group">
                    <a href="#" class="list-group-item active">
                      <h4 class="list-group-item-heading">提问指南</h4>
                      <p class="list-group-item-text">内容与技术相关，有明确的答案，有代码贴代码，附上已尝试过的解决方案</p>
                      <p class="list-group-item-text">良好的排版，正确使用 Markdown 语法</p>
                    </a>
                  </div>

                  <h4>问题标签（至少 1 个）</h4>                
                  
                  <hr class="hr-header">

					<div class="row">
						<div class="col-lg-12" id="tags"></div>
				  </div>
				  <br>
                  <div class="row fix-top">
					<div class="col-lg-12">
						
					
						<div class="input-group">
						  <input type="text" class="form-control" name="question_tags" data-role="simple-input" autocomplete="off">
						  <input type="hidden" class="form-control" name="question_tag" data-role="simple-input" autocomplete="off">
						  <span class="input-group-btn">
						    <button class="btn btn-default" type="button" onclick="tagbutton()">添加</button>
						    <script type="text/javascript">
						    
						    </script>
						  </span>
						  
						 	 
						</div>
					</div><!-- /.col-lg-12-->
					
						<div class="col-lg-12">
							<div class="input-group-btn">
						      <ul class="dropdown-menu pull-left" id="tag-button">
						      </ul>
						    </div><!-- /btn-group -->
                   		</div><!-- /.col-lg-12-->
                   </div>

                  <div class="row">
                  	<div class="col-lg-3"></div>
                  	<div class="col-lg-6 ask-btn btn">
                  		<a href="#" type="button" class="btn btn-primary btn-lg btn-block" onclick="document.form.submit();">提出问题</a>
                  		
                  	</div>
                  </div>

            </div>  
    <!-- right-->
</form>
        </div>
      </div>
    <!-- main-content end-->
    <hr class="hr-header">
    <script>
      $(function(){
        function initToolbarBootstrapBindings() {
          var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
                'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                'Times New Roman', 'Verdana'],
                fontTarget = $('[title=Font]').siblings('.dropdown-menu');
          $.each(fonts, function (idx, fontName) {
              fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
          });
          $('a[title]').tooltip({container:'body'});
            $('.dropdown-menu input').click(function() {return false;})
                .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
            .keydown('esc', function () {this.value='';$(this).change();});

          $('[data-role=magic-overlay]').each(function () { 
            var overlay = $(this), target = $(overlay.data('target')); 
            overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
          });
          $('#voiceBtn').hide();
          // if ("onwebkitspeechchange"  in document.createElement("input")) {
          //   var editorOffset = $('#editor').offset();
          //   $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
          // } else {
          //   $('#voiceBtn').hide();
          // }
        };
        initToolbarBootstrapBindings();  
        $('#editor').wysiwyg();
        window.prettyPrint && prettyPrint();
      });
    </script>

    <!-- foot-->
    <div class="foot">
      <div class="container">
      	<p></p>
        <p>Power by hnust_qa current version  1.0 </p>
      </div>
    </div>
<script src="js/question/searchtag.js"></script>
  </body>
</html>
