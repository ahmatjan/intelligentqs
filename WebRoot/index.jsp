<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <base href="<%=basePath%>">
    
    <title>智能问答主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <!-- Bootstrap core CSS -->
    <link href="./dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="css/custom.css">

    <script src="js/jquery.min.js"></script>
    <script src="./dist/js/bootstrap.js"></script>
	<script type="text/javascript">
		
		$('#myTab a').click(function (e) {
	        e.preventDefault();
	        $(this).tab('show');
	    });
	</script>
  <link rel="stylesheet" href="static/style/custom.css" type="text/css"></link></head>

  <body>
      <div class="container header-bg">
        <nav class="navbar navbar-default header-border header-height" role="navigation">
          
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <div class="row">

              <div class="col-lg-6">
                <form class="navbar-form navbar-left" role="search" action="askQuestion.jsp">
                <div class="input-group ">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button"> Search </button>
                  </span>
                  <input type="text" class="form-control">
                </div><!-- /input-group -->
               </form>
              </div><!-- /.col-lg-6 -->
              <div class="col-lg-6">

                <ul class="nav navbar-nav navbar-right">
                  <li><a href="#">消息</a></li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Account <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="#">我的主页</a></li>
                      <li><a href="#">帐号设置</a></li>
                      <li><a href="#">退出</a></li>
                      <li class="divider">/</li>
                      <li><a href="#">建议反馈</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
          </div><!-- /.navbar-collapse -->
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
                    <li class="active">
                      <a href="#new" data-toggle="tab">最新问题 &nbsp;</a>
                    </li>
                  </ul>
                  <div id="myTabContent" class="tab-content">

                    
                    <div class="tab-pane fade active in" id="new">
                     <ul class="qa-list">

                       <!--  content list -->
            <c:forEach items="${listQuestions }" var="question" begin="0" step="1" varStatus="class">
							 <!--  content list -->
                      <li class="row">
                        <div class="col-md-2">
                          <div class="btn btn-primary btn-lg active">
                            <span class="glyphicon glyphicon-comment"></span> ${question.countOfAnswers }
                            <br>
                          </div>
                        </div>

                        <div class="col-md-8 qa-margin-left">
                            <p><a href="">${question.questionBean.question_title }</a></p>${fn:substring(mashup.who,0,20)}
                            <p><pre><code>${fn:escapeXml(question.questionBean.question_description)}</code></pre></p>
                            <div class="qa-tags">
                              <span class="glyphicon glyphicon-tags"> </span> 
                              <a href="#"><span class="badge">${question.questionBean.question_tags}</span></a>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-eye-open"> </span> 
                              <span class="badge">${question.vpOfQuestion }</span>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-time"> </span> 
                              <span class="badge">${question.questionBean.question_time }</span>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <img src="images/git.png" alt="..." class="qa-img img-circle">
                        </div>
                      </li>
                       <!--  content list end-->
					</c:forEach>
                     </ul>
                     <div  style=text-align:center;>
	                  
							<c:choose>
								 <c:when test="${currentPage == 0 }">首页&nbsp;上一页&nbsp;</c:when>
								 <c:otherwise><a href="disscussQuestion?currentPage=0">首页</a>&nbsp;<a href="disscussQuestion?currentPage=${currentPage - 10}">上一页</a>&nbsp;</c:otherwise>
							</c:choose>
							<c:choose>
						        <c:when test="${currentPage == len - 10 }">下一页&nbsp;末页&nbsp;</c:when>
						        <c:otherwise><a href="disscussQuestion?currentPage=${currentPage + 10 }">下一页</a>&nbsp;<a href="disscussQuestion?currentPage=-1">末页</a>&nbsp;</c:otherwise>
						    </c:choose>
	     				
     				</div>
                     
                  </div>

                    <div class="tab-pane fade" id="hot">
                      
                      <ul class="qa-list">

                       <!--  content list -->
                      
                        <c:forEach items="${listHotQuestions }" var="question" begin="0" step="1" varStatus="class">
							 <!--  content list -->
                      <li class="row">
                        <div class="col-md-2">
                          <div class="btn btn-primary btn-lg active">
                            <span class="glyphicon glyphicon-comment"></span> ${question.countOfAnswers }
                            <br>
                          </div>
                        </div>

                        <div class="col-md-8 qa-margin-left">
                            <p><a href="">${question.questionBean.question_title }</a></p>
                            <p><pre>${fn:escapeXml(question.questionBean.question_description)}</pre></p>
                            <div class="qa-tags">
                              <span class="glyphicon glyphicon-tags"> </span> 
                              <a href="#"><span class="badge">${question.questionBean.question_tags}</span></a>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-eye-open"> </span> 
                              <span class="badge">${question.vpOfQuestion }</span>
                              &nbsp;&nbsp;
                              <span class="glyphicon glyphicon-time"> </span> 
                              <span class="badge">${question.questionBean.question_time }</span>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <img src="images/git.png" alt="..." class="qa-img img-circle">
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
              <!-- ask-->
               <button type="button" class="btn btn-primary btn-lg btn-block  ask" onclick="window.location.href('askQuestion.jsp')">Ask</button>
              <!-- ask-->
               
                  <!-- person-->
                 <div class="row fix-margin-top">
                  
                    <div class="col-md-7">
                      <img src="images/git.png" alt="" class="head-img img-thumbnail">
                    </div>
                    
                    <div class="col-md-5 follow">
                    	<a type="button" class="btn btn-default">
                        	<span class="glyphicon glyphicon-star"></span>问 &nbsp;&nbsp;&nbsp;题 ${totalOfQuestion }个
                     	</a>
						<a type="button" class="btn btn-default">	
							<span class="glyphicon glyphicon-user"></span>已解决 ${countOfSolution }个
						</a>
						<a type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-bookmark"></span>用&nbsp;&nbsp;&nbsp;户 ${totalOfUser }个
							</a>
					</div>
					</div>
                 <!-- person -->
                 
                 <!-- person edit-->
                <div class="row fix-margin-top">
                  <div class="col-md-12">
                       <div class="panel panel-default">
                          <div class="panel-heading">
                            <a href="#" class="panel-title pull-right border-0">Edit</a>
                            <h3 class="panel-title">个人信息</h3>
                          </div>
                          <div class="panel-body">
                            随便写点什么
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
                            <a href="#" class="panel-title pull-right border-0">Add</a>
                            <h3 class="panel-title">热门标签点击搜索</h3>
                          </div>
                          <div class="panel-body">
                          	 <div class="col-md-12">
			                   <c:forEach items="${listTags }" var="tag" begin="0" step="1" varStatus="class">
			                   		<span class="mark-p label label-default">${tag.tags_name }</span>
			                   </c:forEach>
		                  </div>
                          </div>
                        </div>
                  </div>
                </div>
                <!-- person edit-->
                <!-- mark -->

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
        <p>Power by hnust_qa Current version  1.0 </p>
      </div>
    </div>

  </body>
</html>