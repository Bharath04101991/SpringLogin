<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Value Labs | User Details</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
	<center>

		<div style="color: teal; font-size: 30px">Value Labs | User
			Details</div>

		<c:if test="${!empty list}">
			<table border="1" bgcolor="black" width="600px">
				<tr
					style="background-color: teal; color: white; text-align: center;"
					height="40px">

					<td>Id</td>
					<td>Name</td>
					<td>Email</td>
					<td>MobileNo</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
				<c:forEach items="${list}" var="user">
					<tr
						style="background-color: white; color: black; text-align: center;"
						height="30px">

						<td><c:out value="${user.id}" />
						</td>
						<td><c:out value="${user.userName}" />
						</td>
						<td><c:out value="${user.emailId}" />
						</td>
						<td><c:out value="${user.mobileNum}" />
						</td>
						<td><a href="edit?id=${user.id}">Edit</a></td>
						<td><a href="delete?id=${user.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>


		<a href="registration">Add New User</a>
	</center>
</body>
</html>
