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
    <!--end of navbar-->

    <div class="container main-content">
        
        <hr class="hr-header">

        <div class="row">
          <!-- left-->
          <div class="col-lg-9">
              <div class="row tag-title">
                <div class="col-lg-2">
                  <img src="./static/image/git.png" alt="..." class="qa-img img-circle tag-img">
                </div>
                <div class="col-lg-10">
                  <h4>问题分类名称</h4>
                  <p>描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述,我是摘要，我只显示两行...<a href="#">(详细...)</a></p>
                </div>
              </div>
 
              <div class="bs-example bs-example-tabs">
                  
                  <ul id="myTab" class="nav nav-tabs nav-right">
                    <li href="#hot" class=""><a href="#" data-toggle="tab">热门问题</a></li>
                    <li href="#new" class="active">
                      <a href="#" data-toggle="tab">最新问题</a>
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
                      <li class="row">
                        <div class="col-md-2">
                          <div class="btn btn-primary btn-lg active">
                            <span class="glyphicon glyphicon-comment"></span> 15
                            <br>
                          </div>
                        </div>

                        <div class="col-md-8 qa-margin-left">
                            <p><a href="">wys oh my god wys oh my godwys oh my godwys oh my godwys oh my godwys oh my godwys oh my god</a></p>
                            <div class="qa-tags">
                              <span class="glyphicon glyphicon-tags"> </span> 
                              <a href="#"><span class="badge">linux</span></a>
                              <a href="#"><span class="badge">python</span></a>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-eye-open"> </span> 
                              <span class="badge">1.2 K</span>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-time"> </span> 
                              <span class="badge">1 days ago</span>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                        </div>
                      </li>
                       <!--  content list end-->

                      <!--  content list -->
                      <li class="row">
                        <div class="col-md-2">
                          <div class="btn btn-primary btn-lg active">
                            <span class="glyphicon glyphicon-comment"></span> 15
                            <br>
                          </div>
                        </div>

                        <div class="col-md-8 qa-margin-left">
                            <p><a href="">tell me why!!!!!!!!!!!!!!</a></p>
                            <div class="qa-tags">
                              <span class="glyphicon glyphicon-tags"> </span> 
                              <a href="#"><span class="badge">linux</span></a>
                              <a href="#"><span class="badge">java</span></a>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-eye-open"> </span> 
                              <span class="badge">690 K</span>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-time"> </span> 
                              <span class="badge">1 days ago</span>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                        </div>
                      </li>
                      <!--  content list end-->
                      <ul class="pagination">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                      </ul>

                     </ul>
                    </div>

                    <div class="tab-pane fade" id="hot">
                      
                      <ul class="qa-list">

                        <!--  content list -->
                        <li class="row">
                          <div class="col-md-2">
                            <div class="btn btn-primary btn-lg active">
                              <span class="glyphicon glyphicon-comment"></span> 15
                              <br>
                            </div>
                          </div>

                          <div class="col-md-8 qa-margin-left">
                              <p><a href="">wys oh my god wys oh my godwys oh my godwys oh my godwys oh my godwys oh my godwys oh my god</a></p>
                              <div class="qa-tags">
                                <span class="glyphicon glyphicon-tags"> </span> 
                                <a href="#"><span class="badge">linux</span></a>
                                <a href="#"><span class="badge">python</span></a>
                                &nbsp;&nbsp;
                                <span class="glyphicon glyphicon-eye-open"> </span> 
                                <span class="badge">1.2 K</span>
                                &nbsp;&nbsp;
                                <span class="glyphicon glyphicon-time"> </span> 
                                <span class="badge">1 days ago</span>
                              </div>
                          </div>

                          <div class="col-md-2">
                              <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                          </div>
                        </li>
                        <!--  content list end-->

                        <!--  content list -->
                        <li class="row">
                          <div class="col-md-2">
                            <div class="btn btn-primary btn-lg active">
                              <span class="glyphicon glyphicon-comment"></span> 15
                              <br>
                            </div>
                          </div>

                          <div class="col-md-8 qa-margin-left">
                              <p><a href="">tell me why!!!!!!!!!!!!!!</a></p>
                              <div class="qa-tags">
                                <span class="glyphicon glyphicon-tags"> </span> 
                                <a href="#"><span class="badge">linux</span></a>
                                <a href="#"><span class="badge">java</span></a>
                                &nbsp;&nbsp;
                                <span class="glyphicon glyphicon-eye-open"> </span> 
                                <span class="badge">690 K</span>
                                &nbsp;&nbsp;
                                <span class="glyphicon glyphicon-time"> </span> 
                                <span class="badge">1 days ago</span>
                              </div>
                          </div>

                          <div class="col-md-2">
                              <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                          </div>
                        </li>
                        <!--  content list end-->
                        <ul class="pagination">
                          <li><a href="#">&laquo;</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">5</a></li>
                          <li><a href="#">&raquo;</a></li>
                        </ul>
                      </ul>
                    </div>
                   
                  </div>
                </div>
          </div>
          <!-- left-->

          <!-- right-->
          <div class="col-lg-3">
            <!--follow-->
            <div class="row tag-follow">
                <div class="col-lg-12">
                  <a href="#">
                    <!-- <button type="button" class="btn btn-primary btn-lg ask">关注</button> -->
                    <a href="#" type="button" class="btn btn-primary btn-lg btn-block">关注</a>
                  </a>
                </div>
                <div class="col-lg-12">
                  <p></p>
                  <p class="text-left">共 <a href="#">840</a> 人关注该问题</p>
                  <hr class="hr-aside">
                </div>
            </div><!-- end of follow -->

            <!-- hot-question -->
            <div class="row hot-question">
              <div class="col-lg-8">
                <h4>热门问题</h4>
              </div>
              <div class="col-lg-4">
                <p></p>
                <p class="more"><a href="#">更多</a></p>
              </div>
              <div class="col-lg-12 related-list">
                <ul class="list-unstyled">
                  <li><a href="#" target="_blank">MongoDB数据不存在时插入，已存在...</a></li>
                  <li><a href="#" target="_blank">Node.js中如何插入几万条MongoDB...</a></li>
                  <li><a href="#" target="_blank">mongodb返回刚插入数据的id用什么...</a></li>
                  <li><a href="#" target="_blank">往mongodb中插入数据，但是大部分...</a></li>
                  <li><a href="#" target="_blank">MongoDB数据不存在时插入，已存在...</a></li>
                  <li><a href="#" target="_blank">Node.js中如何插入几万条MongoDB...</a></li>
                </ul>
                <hr class="hr-aside">
              </div>
            </div><!-- end of hot-question -->

            <!-- hot-user -->
            <div class="row hot-user">
              <div class="col-lg-8">
                <h4>风云人物</h4>
              </div>
              <div class="col-lg-4">
                <p></p>
                <p class="more"><a href="#">更多</a></p>
              </div>
              <div class="col-lg-12 related-list">
                <ul class="list-unstyled row">
                  <li>
                    <div class="row">                  
                    <div class="col-md-3 col-md-offset-1">
                      <img src="./static/image/git.png" alt="" class="qa-img img-circle">
                    </div>                    
                    <div class="col-md-8">                   
                      <p>
                        <a href="#">admin</a>
                        <br>
                        <a href="">积分：300</a>
                        <br> 
                        <a href="">回答问题数：100</a>
                      </p>
                    </div>

                 </div>
                  </li>
                  <li><span class="col-md-6"><a href="#" target="_blank">admin</a></span><span class="col-md-6">回答问题数：50</span></li>
                  <li><span class="col-md-6"><a href="#" target="_blank">admin</a></span><span class="col-md-6">回答问题数：50</span></li>
                  <li><span class="col-md-6"><a href="#" target="_blank">admin</a></span><span class="col-md-6">回答问题数：50</span></li>
                  <li><span class="col-md-6"><a href="#" target="_blank">admin</a></span><span class="col-md-6">回答问题数：50</span></li>
                  <li><span class="col-md-6"><a href="#" target="_blank">admin</a></span><span class="col-md-6">回答问题数：50</span></li>
                  <li><span class="col-md-6"><a href="#" target="_blank">admin</a></span><span class="col-md-6">回答问题数：50</span></li>
                </ul>
                <hr class="hr-aside">
              </div>
            </div><!-- end of hot-user -->

            
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
        <p>Power by nxhnust Current version  0.0.1 </p>
      </div>
    </div>
    <!--foot-->
  </body>
</html>
