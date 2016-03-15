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

		<div style="color: teal; font-size: 30px">ChangePassword</div>


		<form:form id="changePasswordForm" modelAttribute="form" commandName="form" method="get" action="resetPassword" >

			<table width="400px" height="150px">
			     <tr>
					<td><form:label path="id">Id</form:label></td>
					<td><form:input path="id" value="${form.id}" /></td>
					<td colspan="3"><font color="red"><form:errors path="id" /></font></td>
					<td></td>
				</tr>
				<%-- <tr>
					<td><form:label path="mailId">EmailId</form:label></td>
					<td><form:input path="mailId" value="${form.mailId}" /></td>
					<td colspan="3"><font color="red"><form:errors path="mailId" /></font></td>
					<td></td>
				</tr> --%>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" value="${form.password}"/></td>
					<td colspan="3"><font color="red"><form:errors path="password" /></font></td>
				</tr>
				
                <tr>
				       <td><form:label path="newPassword">NewPassword</form:label></td>
                		<td><form:input path="newPassword" /></td>
                		<td colspan="3"><font color="red"><form:errors path="newPassword" /></font></td>
                </tr>
                <tr>
				    <td><input type="submit" onclick="document.form.reset();" value="ChangePassword"/></td>
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