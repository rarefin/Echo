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
<title>change Password</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(document).ready(function()

	{
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

		<div id="grad1">
			<table align="center">
				<tr>
					<td align="center">
						<table align="center">
							<tr>
								<td align="center">
									<div id="cssmenu">
										<ul>
											<li><a href="../Echo/myEchos"><%=session.getAttribute("userName")%></a></li>
											<li><a href="../Echo/home"><span>Home</span></a></li>
											<li><a href="../Echo/postEcho"><span>Post
														Echo</span></a></li>
											<li><a href="../Echo/changePassword"><span>change
														password</span></a></li>
											<li><a href="../Echo/deleteAccount?action=deleteAccount"
												onclick="return confirm('Are you sure you want to delete your account?')"><span>Delete
														Account</span></a></li>
											<li class="last"><a href="../Echo/logOut"><span>sign
														out</span></a></li>
										</ul>
									</div>
									<div id="grad2"></div>
							</tr>
							</td>
						</table> <br>
					<br> <%
 	Object uid = session.getAttribute("userName");
 	if (uid == null) {
 		String redirectURL = "http://localhost:8080/Echo/login";
 		response.sendRedirect(redirectURL);
 	}
 %>
						<form name="changePassword" method="post" action="">
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
									<td>Enter Old Password</td>
									<td><input type="password" name="oldPassword"
										id="oldPassword" placeholder="Old password"></td>
								</tr>
								<tr>
								<tr>
									<td>Enter New Password</td>
									<td><input type="password" name="newPassword"
										id="newPassword" placeholder="New password"></td>
								</tr>
								<tr>
									<td>Re-Enter New Password</td>
									<td><input type="password" name="reTypePassword"
										id="reTypePassword" placeholder="Re-enter password"></td>
								</tr>
							</table>
							<br>
							<input type="submit" class="button" value="DONE"> <br></br>
							<br>
							
						</form>
					</td>
					</div>
</body>
</html>