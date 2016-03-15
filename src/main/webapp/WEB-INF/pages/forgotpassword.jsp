<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
<center>

		<div style="color: teal; font-size: 30px">Forgot Password</div>


		<form:form id="forgotpassword" modelAttribute="forgotPasswordForm" method="post" action="getpassword" commandName="forgotPasswordForm">
		
		<h4>${errorMessage}</h4>
			<table width="400px" height="150px">
				<tr>
					<td><form:label path="emailId">Email Id</form:label></td>
					<td><form:input path="emailId" /></td>
					<td><font color="red"><form:errors path="emailId" /></font></td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input type="submit" onclick="document.form.reset();" value="Get Password" />
					</td>
				</tr>
			</table>
			
		</form:form>

	</center>
</body>
</html>