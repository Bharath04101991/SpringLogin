<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Value Labs | LoginForm</title>
<script type="text/javascript">
/* <script>
function clearAutofill() {
    if ( navigator.userAgent.toLowerCase().indexOf('chrome') >= 0 ) {
        $('input[autocomplete="off"]').each( function(){
            $(this).val('');
        });
    }
} */

</script>

</head>
<body>

	<center>

		<div style="color: teal; font-size: 30px">Sample Login Application</div>


		<form:form id="loginform" modelAttribute="usersForm" method="post" action="login" commandName="usersForm"
		autocomplete="off">
		
		    <h4>${message}</h4>
			<table width="400px" height="150px">
				<tr>
					<td><form:label path="userEmail">Email/Mobile</form:label></td>
					<td><form:input path="userEmail" /></td>
					<td><font color="red"><form:errors path="userEmail" /></font></td>
					<td></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" /></td>
					<td><font color="red"><form:errors path="password" /></font></td>
					</tr>
				
				<tr>
				    <td><a href="registration">Register</a></td>
				    <td></td>
					<td><a href="forgotpassword">Forgot Password..??</a></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" onclick="document.form.reset();" value="Login" />
					</td>
				</tr>
			</table>
			
		</form:form>

	</center>
</body>
</html>
