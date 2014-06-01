<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="en" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Forgot Password</title>
<script src="jquery.js"></script>
<script>
	$(document).ready(function() {
		var i = 1000;
		while (i > 0) {
			$("#E").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#C").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#H").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#O").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#fp").css("color", "#306").fadeIn(2000).fadeOut(3000);
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
		<br> <br> <br> <label id="fp" for="forgetPassword"
			style="font-size: 25px; font-weight: bold; color: #306">Please
			Enter your Email Address to recover Password</label><br> <br>
		<form name="forgotPassword" method="post" action="">
			<c:if test="${fn:length(error) > 0 }">
				<div class="span6 well text-error"
					style="color: red; font-style: italic;">
					<c:forEach items="${error}" var="entry">
                        ${entry.value}<br>
					</c:forEach>
				</div>
			</c:if>
			<br>
			<br>
			<table>
				<tr>
					<td>E-mail Address</td>
					<td><input type="text" id="email" name="email"
						placeholder="Enter E-mail Address"></td>
				</tr>
			</table>
			<br> <input type="submit" class="button" value="Submit">
			<br></br> <br>
		</form>
	</div>
</body>
</html>