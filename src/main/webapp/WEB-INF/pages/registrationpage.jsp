<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>

		<div style="color: teal; font-size: 30px">Registration</div>


		<form:form id="registrationform" modelAttribute="registrationForm" method="post" action="register" autocomplete="off">

		    <h4>${message}</h4>
			<table width="400px" height="150px">
				<tr>
					<td><form:label path="userName">User Name</form:label></td>
					<td><form:input path="userName" /></td>
					<td colspan="3"><font color="red"><form:errors path="userName" /></font></td>
					<td></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" /></td>
					<td colspan="3"><font color="red"><form:errors path="password" /></font></td>
				</tr>
				<tr>
				       <td><form:label path="emailId">EmailId</form:label></td>
                		<td><form:input path="emailId" /></td>
                		<td colspan="3"><font color="red"><form:errors path="emailId" /></font></td>
                </tr>
                
                <tr>
				       <td><form:label path="mobileNum">Mobile</form:label></td>
                		<td><form:input path="mobileNum" /></td>
                		<td colspan="3"><font color="red"><form:errors path="mobileNum" /></font></td>
                </tr>

				<tr>
				    <td><input type="submit" onclick="document.form.reset();" value="Register"/></td>
				    <td></td>
				    <td></td>
                    <td><input type="reset"  value="Reset" /></td>
                </tr>
                <tr>
                   <td><a href="welcome">Go back to login page</a></td>
                </tr>
			</table>

		</form:form>

	</center>
</body>
</html>