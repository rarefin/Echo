<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="en" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Sign up to ECHO</title>
<head>
<title>Sign up</title>
<script src="jquery.js"></script>
<script>
	$(document).ready(function() {
		var i = 1000;
		while (i > 0) {
			$("#E").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#C").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#H").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#O").css("color", "#306").fadeOut(2000).fadeIn(2000);
			i = i - 1;
		}
	});
</script>
<link rel="stylesheet" type="text/css" href="styleSheet.css">
</head>
<body>
	<div id="grad1" align="center">
		<label id="E" style="font-size: 30px;">E</label> <label id="C"
			style="font-size: 20px">C</label> <label id="H"
			style="font-size: 20px">H</label> <label id="O"
			style="font-size: 20px">O</label>
		<table align="center" cellpadding="0" cellspacing="4px">
			<tr>
				<td align="right" style="width: 1250px;"><label
					style="font: 'Arial Black', Gadget, sans-serif; font-size: 16px">Already
						have an account? </label><a href="../Echo/login" class="button blue"
					style="height: 18px; width: 70px;">Sign in</a></td>
			</tr>
		</table>
		<div id="grad2" style="width: 1200px;"></div>

		<br>
		<label for="Registration"
			style="font-size: 30px; font-weight: bold; color: #306">Create
			your Echo Account</label><br>
		<br>
		<form name="register" <c:url value="/register"/>"  method="post"
			action="">
			<c:if test="${fn:length(error) > 0 }">
				<div class="span6 well text-error"
					style="color: red; font-style: italic;">
					<c:forEach items="${error}" var="entry">
                        ${entry.value}<br>
					</c:forEach>
				</div>
			</c:if>
			<br>

			<table>

				<tr>
					<td>First Name</td>
					<td><input type="text" id="firstName" name="firstName"
						placeholder="Enter First Name"></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" id="lastName" name="lastName"
						placeholder="Enter Last Name"></td>
				</tr>
				<tr>
					<td>E-mail Address</td>
					<td><input type="text" id="email" name="email"
						placeholder="Enter E-mail Address"></td>
				</tr>

				<tr>
					<td>User Name</td>
					<td><input type="text" id="userName" name="userName"
						placeholder="Enter User Name"></td>
				</tr>

				<tr>
					<td>Enter Password</td>
					<td><input type="password" id="password" name="password"
						placeholder="Enter password"></td>
				</tr>
				<tr>
					<td>Re-Enter Password</td>
					<td><input type="password" id="reTypePassword"
						name="reTypePassword" placeholder="Confirm password"></td>
				</tr>
			</table>
			<br>
			<input type="submit" class="button" value="Sign up"> <br></br>
			<br> 
	</div>
	</form>
</body>
</html>