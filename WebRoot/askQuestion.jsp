<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提问</title>
</head>
<body>
<h2>提问</h2>
<form action="askQuestionServlet" method="post">
	问题：<input type="text" id="question_title" name="question_title"/><br/>
	问题描述：<textarea id="question_description" name="question_description"></textarea><br/>
	标签：<input type="text" id="question_tags" name="question_tags"/>
	<input type="submit" value="提问"/>
</form>
</body>
</html>