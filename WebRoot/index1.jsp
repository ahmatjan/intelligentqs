<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>  
    <title>我的主页</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
  </head>
  
  <body>
  	<a href="getAllTagsInfo">标签列表</a>
  	<ul>
	  <c:forEach items="${tagsInfoList }" var="a" begin="0" step="1" varStatus="class">
			<li>
			<a href="#">
				<div class="float">
				 	${a.tags_name }
				</div>
			</a>
			</li>
	</c:forEach>
</ul>
    ${Msg }
    <form action="addTagInfo" method="post">
    	标签名:<input type="text" name="tags_name"><br>
    	<button type="submit">新增标签</button><br><br><hr>
    </form>
    <a href="getHotAnswersInfo">得到所有的问题</a>
    	<div>
		    <table bordercolor="blue" border="1">
		    <tr>
		    			<th bgcolor="red">内容</th>
		    			<th bgcolor="red">时间</th>
		    			<th bgcolor="red">标记</th>
		    			<th bgcolor="red">用户ID</th>
		    			<th bgcolor="red">问题ID</th>
		    			<th bgcolor="red">是否是最答案</th>
		    			<th bgcolor="red">操作</th>
		    		</tr>
		    <c:forEach items="${hotAnswers }" var="a" begin="0" step="1" varStatus="class">
		    		<tr bgcolor="green">
		    			<td>${a.answer_description }</td>
		    			<td>${a.answer_time }</td>
		    			<td>${a.answer_mark }</td>
		    			<td>${a.answer_user_id }</td>
		    			<td>${a.question_id }</td>
		    			<td>${a.answer_best }</td>
		    			<td><a href="deleteAnswer?answerId=${a.answer_id }">删除</a></td>
		    		</tr>
			</c:forEach>
			</table>
		</div>
		<form action="addAnswerInfo" method="post">
    	回答内容:<br><textarea rows="10" cols="60" name="answer_description"></textarea>
    	<button type="submit">回答</button>
    </form>
    ${Msg }
  </body>
</html>
