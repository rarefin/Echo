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
<title>My Echos</title>
<script src="jquery.js"></script>
<script>
	var i = 1000;
	$(document).ready(function() {
		while (i > 0) {
			$("#E").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#C").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#H").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#O").css("color", "#306").fadeOut(2000).fadeIn(2000);
			$("#me").css("color", "#306").fadeOut(2000).fadeIn(2000);
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

		<table align="center">
			<tr>
				<td align="center">


					<div id="cssmenu">
						<ul>
							<li><a href="../Echo/myEchos"><%=session.getAttribute("userName")%></a></li>
							<li><a href="../Echo/home"><span>Home</span></a></li>
							<li><a href="../Echo/postEcho"><span>Post Echo</span></a></li>
							<li><a href="../Echo/changePassword"><span>change
										password</span></a></li>
							<li><a href="../Echo/deleteAccount?action=deleteAccount"
								onclick="return confirm('Are you sure you want to delete your account?')"><span>Delete
										Account</span></a></li>
							<li class="last"><a href="../Echo/logOut"><span>sign
										out</span></a></li>
						</ul>
					</div>



					<div id="grad2"></div> <%
 	Object uid = session.getAttribute("userName");
 	if (uid == null) {
 		String redirectURL = "http://localhost:8080/Echo/login";
 		response.sendRedirect(redirectURL);
 	}
 %>
				</td>
			</tr>
		</table>
		<br>

		<table id="main" align="center" style="width: 550px;">
			<tr>
				<td style="font-size: x-large;" align="center"><br> <label
					id="me">My Echos</label><br> <br></td>
			</tr>
		</table>


		<c:if test="${fn:length(myEchos) > 0 }">
			<div class="span6 well text-error">
				<c:forEach items="${myEchos}" var="entry">
					<table id="main" align="center" cellspacing="0" cellpadding="0"
						style="border-spacing: 0px; border-collapse: collapse;">
						<tr>
							<table style="border-spacing: 0px; border-collapse: collapse;">
								<td id="icon" align="left"
									style="max-width: 150px; height: 100px; background-color: #CFF">
									<img src="images.jpg" width="94px" height="100px">
								</td>
								<td id="sideOfIddata" align="left"
									style="width: 400px; height: 100px; background-color: #CFF; font-size: 15px;">
									<b style="font-size: x-large;">${entry.userName}</b><br>
									${entry.createdDate}<br> ${entry.geoLocation}<br> <label>Category:</label><a
									style="height: 15px; width: 85px; color: #000000"
									href="../Echo/categoryBasedEcho?issueCategory=${entry.issueCategory}">${entry.issueCategory}</a>
								</td>
								<td id="deleteButton" align="right"
									style="height: 100px; width: 50px; background-color: #CFF">
									<a style="width: 25px; height: 25px;" id="deletSS"
									href="../Echo/deleteEcho?deleteEchoId=${entry.id}"><img
										src="delete_button.jpg" width="25px" height="25px"></a><br></br>
									<br></br> <br>
								</td>
							</table>
						</tr>
						<tr>
							<table cellpadding="0" cellspacing="0"
								style="border-spacing: 0px; border-collapse: collapse;">
								<td id="showPost"
									style="width: 550px; background-color: #CFF; font-size: 20px;">
									<p>${entry.echo}</p>
								</td>
							</table>
						</tr>
						<tr>
							<table style="border-spacing: 0px; border-collapse: collapse;">
								<td align="center" id="image//"
									style="width: 550px; height: 300px; background-color: #CFF">
									<img class="fixedImage"
									src="http://localhost:8080/Echo/image?echoId=${entry.id}">
								</td>
							</table>
						</tr>
						<tr>
							<table cellpadding="0" cellspacing="0"
								style="border-spacing: 0px; border-collapse: collapse;">
								<td id="UserInformation" align="center"
									style="width: 550px; background-color: #CFF; font-size: 18px;">
									<p style="font-size: 16px;">
										Latitude:${entry.latitude}<br>
										Longitude:${entry.longitude}<br>
										Altitude:${entry.altitude}<br> Speed:${entry.speed}
									</p>
								</td>
							</table>
						</tr>
					</table>
					<br>

				</c:forEach>
			</div>
		</c:if>
	</div>

</body>
</html>