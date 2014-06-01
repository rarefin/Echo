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
<title>Welcome to ECHO</title>
<script src="jquery.js"></script>
<script>
	var i = 1000;
	$(document).ready(function() {
		while (i>0) {
			$("#a").css("color", "#306").fadeOut(3000).fadeIn(3000);
			$("#E").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#C").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#H").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#O").css("color", "#306").fadeOut(2000).fadeIn(2000);
			i=i-1;
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
				<td align="right" style="width: 1250px;">
					<form name="login" action="" method="post">
						<label
							style="font: 'Arial Black', Gadget, sans-serif; font-size: 16px">User
							name</label> <input type="text" name="userName" id="userName"
							tabindex="1" placeholder="UserName"> <label
							style="font: 'Arial Black', Gadget, sans-serif; font-size: 16px">Password</label>
						<input type="password" name="password" id="password" tabindex="1"
							placeholder="Password"> <br> <input type=submit
							class="button" allign="right" style="height: 30px; width: 65px;"
							value="SIGN IN">
						<c:if test="${fn:length(error) > 0 }">
							<div class="span6 well text-error"
								style="color: red; font-style: italic;">
								<c:forEach items="${error}" var="entry">
                        ${entry.value}<br>
								</c:forEach>
							</div>
						</c:if>
						<br>
					</form> <br>
					<div id="grad2"></div>
				</td>
			</tr>
		</table>
		<br></br> <label style="sscolor: #303"><b>Forgot
				password???<br> <a href="../Echo/forgotPassword">Click Here</a>
		</b></label><br></br><label id="a" style="font-size: 25px; color: #C09">Post On ECHO,Spread The Incidents</label>


		<p>
			New To Echo????Register now,,,,,,Its Free<br></br> <a
				href="../Echo/register">Sign Up</a>
		</p>
	</div>

</body>
</html>
