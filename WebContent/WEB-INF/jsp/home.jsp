<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
#menuBar form div {
	font-family: Verdana, Geneva, sans-serif;
}
#menuBar form div {
	font-family: Tahoma, Geneva, sans-serif;
}
#menuBar form div {
	font-family: Trebuchet MS, Arial, Helvetica, sans-serif;
}
#menuBar form div {
	font-family: Lucida Sans Unicode, Lucida Grande, sans-serif;
}
#container #olderEcho p label div b {
	font-style: italic;
}
#container #shiwPost pre label b {
	font-weight: bold;
}
#container #shiwPost pre label b {
	font-size: 36;
}
#container #shiwPost pre label b {
	font-weight: normal;
}
</style>
<body>
<div id="container" style="background-color:#FF6;width:1366px;
height:700px">
<div id = "header" style="background-color:#F30; width:1366px;height:140px">
<h1 align="center" style="margine-bottom: 0; font-weight: bold;"><img src="../images.jpg" width="176" height="58">Echo.com</h1>
<p align="center" style="margine-bottom: 0; font-weight: bold;">This web site allows you to post message called Echo</p>
</div>

<div id="menuBar" style="background-color:#FF9;width:1366px;height:25px">
  <form name="form1" method="post" action="">
    <div align="center">
      <input type="submit" name="Home" id="Home" value="Home" style="background-color:#66C">
      <input type="submit" name="Changepass" id="Changepass" value="Change Profile" style="background-color:#66C" >
      <input type="submit" name="Mypost" id="Mypost" value="My Posts" style="background-color:#66C">
      <input type="submit" name="Search Echo" id="Search Echo" value="Search Echo" style="background-color:#66C">
      <input type="submit" name="LogOut" id="LogOut" value="LogOut" style="background-color:#66c">
    </div>
  </form>
</div>
<div id="showPost" style="background-color:#FC9; width:1300px;height:200px; font-size:40px;">
  <pre><label><b>   Post Echo</b></label>       <input type="submit" name="post" id="post" value="Post" style="background-color:#36F">
    <textarea name="ecchoPostArea" cols="45" rows="5"></textarea> 
    Insert Image <span class="form-item"><img id="img_prev" src="#" alt="your image"/></span></pre> 
</div>
<div id="olderEcho" style="alignment-adjust: central; background-color:#FC6; width: 1366px; height: 650px; font-size: 36px;">
<p>
<label>
  <div align="center"><b>Older ECHO</b> </label>
  <pre> <img src="../Untitled.png" width="80" height="81"> <textarea name="echoByUser1" id="echoByUser1" cols="45" rows="4"> </textarea>
 
 
 <img src="../Untitled2.png" width="80" height="81"> <textarea name="echoByUser1" id="echoByUser1" cols="45" rows="4"> </textarea>

 
 <img src="../Untitled.png" width="80" height="81"> <textarea name="echoByUser1" id="echoByUser1" cols="45" rows="4"> </textarea> 
 
 
 <img src="../Untitled2.png" width="80" height="81"> <textarea name="echoByUser1" id="echoByUser1" cols="45" rows="4"> </textarea> 
  

  </pre>
</div>
</body>
</html>