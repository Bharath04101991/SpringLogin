<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<form:form id="loginform" modelAttribute="usersForm" method="post" action="login" >
<h1>login is sucessful</h1>
<a href="list">Users List</a>
<a href="changePasswordpage">ChangePassword</a>
</form:form>
</body>
</html>