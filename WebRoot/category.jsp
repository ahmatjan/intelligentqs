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
              <div class="bs-example bs-example-tabs">
                <!--问题与标签切换-->
                <ul id="myTab" class="nav nav-tabs">
                  <li href="#prolem" class="active">
                    <a href="#" data-toggle="tab">问题分类</a>
                  </li>
                  <li href="#tag" class="">
                    <a href="#" data-toggle="tab">标签导航</a>
                  </li>
                </ul>
                
                <div id="myTabContent" class="tab-content nav">

                  <div class="row tab-pane fade active in" id="prolem">   
                    <div class="col-lg-12">
                      <!--问题一级分类切换-->
                      <ul id="myTab" class="category-list">
                        <li href="#level-1" class="active">
                          <a href="#" data-toggle="tab">一级分类一</a>
                        </li> 
                        <li href="#level-2" class="">
                          <a href="#" data-toggle="tab">一级分类二</a>
                        </li>
                        <li href="#level-3" class="">
                          <a href="#" data-toggle="tab">一级分类三</a>
                        </li>
                        <li href="#level-4" class="">
                          <a href="#" data-toggle="tab">一级分类四</a>
                        </li>
                        <li href="#level-5" class="">
                          <a href="#" data-toggle="tab">一级分类五类五类五</a>
                        </li>
                        <li href="#level-6" class="">
                          <a href="#" data-toggle="tab">一级分类六类六</a>
                        </li>
                        <li href="#level-7" class="">
                          <a href="#" data-toggle="tab">一级分类七</a>
                        </li>
                        <li href="#level-8" class="">
                          <a href="#" data-toggle="tab">一级分类八</a>
                        </li>
                        <li href="#level-9" class="">
                          <a href="#" data-toggle="tab">一级分类九</a>
                        </li>
                        <li href="#level-10" class="">
                          <a href="#" data-toggle="tab">一级分类十</a>
                        </li>
                        <li href="#level-11" class="">
                          <a href="#" data-toggle="tab">一级分类十一</a>
                        </li>
                        <li href="#level-12" class="">
                          <a href="#" data-toggle="tab">一级分类十二</a>
                        </li>
                      </ul>                      
                    </div>
                    <div class="col-lg-12">
                      <hr class="hr-category">
                    </div>
                    <div id="myTabContent" class="col-lg-12">
                      <div class="row tab-pane fade active in" id="level-1">
                        <div class="col-lg-12 son">
                          <!--问题二级分类切换-->
                          <ul id="myTab" class="category-list">
                            <li href="#level-1-1" class="active">
                              <a href="#" data-toggle="tab">二级分类一</a>
                            </li> 
                            <li href="#level-1-2" class="">
                              <a href="#" data-toggle="tab">二级分类二</a>
                            </li>
                            <li href="#level-1-3" class="">
                              <a href="#" data-toggle="tab">二级分类三</a>
                            </li>
                            <li href="#level-1-4" class="">
                              <a href="#" data-toggle="tab">二级分类四</a>
                            </li>
                            <li href="#level-1-5" class="">
                              <a href="#" data-toggle="tab">二级分类五类五类五</a>
                            </li>
                            <li href="#level-1-6" class="">
                              <a href="#" data-toggle="tab">二级分类六类六</a>
                            </li>
                            <li href="#level-1-7" class="">
                              <a href="#" data-toggle="tab">二级分类七</a>
                            </li>
                            <li href="#level-1-8" class="">
                              <a href="#" data-toggle="tab">二级类八</a>
                            </li>
                            <li href="#level-1-9" class="">
                              <a href="#" data-toggle="tab">二级分类九</a>
                            </li>
                            <li href="#level-1-10" class="">
                              <a href="#" data-toggle="tab">二级分类十</a>
                            </li>
                            <li href="#level-1-11" class="">
                              <a href="#" data-toggle="tab">二级分类十一</a>
                            </li>
                            <li href="#level-1-12" class="">
                              <a href="#" data-toggle="tab">二级分类十二</a>
                            </li>
                          </ul>
                        </div>
                        <div class="col-lg-12">
                          <hr class="hr-category">
                        </div>
                        <div id="myTabContent" class="col-lg-12">
                          <div class="row tab-pane fade active in" id="level-1-1">
                            <div class="col-lg-4">
                              <div class="row question-category">
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="question_category_detail.jsp"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-lg-4">
                              <div class="row question-category">                                
                                <div class="col-lg-3">
                                  <p></p>
                                  <a href="#">
                                    <img src="./static/image/git.png" alt="..." class="qa-img img-circle">
                                  </a>
                                </div>
                                <div class= "col-lg-9">
                                  <a href="#"><h5>三级分类一</h5></a>
                                  <p>描描述,我是摘要，我只显示两行......</p>
                                </div>
                              </div>
                            </div>
                          </div>
                          
                          <div class="row tab-pane fade" id="level-1-2"></div>
                          <div class="row tab-pane fade" id="level-1-3"></div>
                          <div class="row tab-pane fade" id="level-1-4"></div>
                          <div class="row tab-pane fade" id="level-1-5"></div>
                          <div class="row tab-pane fade" id="level-1-6"></div>
                          <div class="row tab-pane fade" id="level-1-7"></div>
                          <div class="row tab-pane fade" id="level-1-8"></div>
                          <div class="row tab-pane fade" id="level-1-9"></div>
                          <div class="row tab-pane fade" id="level-1-10"></div>
                          <div class="row tab-pane fade" id="level-1-11"></div>
                          <div class="row tab-pane fade" id="level-1-12"></div>
                        </div>
                      </div>

                      <div class="row tab-pane fade" id="level-2"></div>
                      <div class="row tab-pane fade" id="level-3"></div>
                      <div class="row tab-pane fade" id="level-4"></div>
                      <div class="row tab-pane fade" id="level-5"></div>
                      <div class="row tab-pane fade" id="level-6"></div>
                      <div class="row tab-pane fade" id="level-7"></div>
                      <div class="row tab-pane fade" id="level-8"></div>
                      <div class="row tab-pane fade" id="level-9"></div>
                      <div class="row tab-pane fade" id="level-10"></div>
                      <div class="row tab-pane fade" id="level-11"></div>
                      <div class="row tab-pane fade" id="level-12"></div>
                    
                    </div>

                  </div>


                  <div class="row tab-pane fade" id="tag">
                    <div class="col-lg-4">
                      <div class="row tag-list">
                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href="tag_category_detail.jsp"><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            <a href=""><span class="mark-p label label-default">objective-c</span></a>
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                      </div>
                    </div>

                    <div class="col-lg-4">
                      <div class="row tag-list">
                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            <a href=""><a href=""><span class="mark-p label label-default">objective-c</span></a>
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            <a href=""><a href=""><span class="mark-p label label-default">objective-c</span></a>
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            <a href=""><a href=""><span class="mark-p label label-default">objective-c</span></a>
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            <a href=""><a href=""><span class="mark-p label label-default">objective-c</span></a>
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>
                        
                      </div>
                    </div>

                    <div class="col-lg-4">
                      <div class="row tag-list">
                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>                      
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                          
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                        <section>
                          <h4 class="col-lg-12">iOS开发</h4>
                          <ul class="col-lg-12">
                            <a href=""><span class="mark-p label label-default">ios</span></a>
                            <a href=""><span class="mark-p label label-default">iphone</span></a>
                            <a href=""><span class="mark-p label label-default">ipad</span></a>
                            <a href=""><span class="mark-p label label-default">objective-c</span></a>
                            <a href=""><span class="mark-p label label-default">sqlites</span></a>
                            <a href=""><span class="mark-p label label-default">safari</span></a>
                            <a href=""><span class="mark-p label label-default">xcode</span></a>
                            <a href=""><span class="mark-p label label-default">phonegap</span></a>
                            <a href=""><span class="mark-p label label-default">cocoa</span></a>
                            <a href=""><span class="mark-p label label-default">javascript</span></a> 
                          </ul>
                        </section>

                      </div>
                    </div>

                  </div>
                 
                </div>
              </div>
          </div>
          <!-- left-->

          <!-- right-->
          <div class="col-lg-3">
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
