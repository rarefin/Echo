<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Post Echo</title>


<script>
		$(document).ready(function()
  		{
    		while(1==1){
				$("#postecho").css("color","#009").fadeIn(2000).fadeOut(2000);
		}
});
		
		$("submit").click(function(event){
		     if($("text").val()==''){
		          event.PreventDefault();
		          alert("your message");
		     }
		});
		
</script>

<script type="text/javascript">

function postEchoValidation(){
	if (document.postEcho.geoLocation.value == ""){
		alert ( "Please refresh your browser and click share location to get your location" );
		return false;
		}
	
	if (document.postEcho.text.value == ""){
	alert ( "Please enter text in the echo field" );
	document.postEcho.text.focus();
	return false;
	}
	if (document.postEcho.charCount.value >140) {
		alert ("Echo must be limited to 140 characters");
		document.postEcho.charCount.focus();
		return false;
		}
	
	if (document.postEcho.preDefinedCategory.value == ""){
		alert ( "Please select Issue Category" );
		document.postEcho.preDefinedCategory.focus();
		return false;
		}
	if (document.postEcho.preDefinedCategory.value == "other" && document.postEcho.userDefinedCategory.value == "" ){
		alert ( "Please Specify Issue Category" );
		document.postEcho.userDefinedCategory.focus();
		return false;
		}
	if (document.postEcho.photo.value == ""){
		alert ( "Please upload a photo" );
		document.postEcho.photo.focus();
		return false;
		}
	return true;
	}


function check() {
    var el = document.getElementById("combo");
    var str = el.options[el.selectedIndex].text;
    if(str == "Other,Please Specify:") {
        show();
    }else {
        hide();
    }
}
function hide(){
    document.getElementById('issueCategoryField').style.visibility='hidden';
}
function show(){
    document.getElementById('issueCategoryField').style.visibility='visible';
}

        var mapDiv;
        window.onload = function () {
            mapDiv = document.getElementById('map');
            mapDiv.innerHTML = 'Trying to get your location... Please wait...';
            if (navigator.geolocation)
                navigator.geolocation.getCurrentPosition(handleGetCurrentPosition, handleGetCurrentPositionError);
        }


        function handleGetCurrentPosition(location) {

            var position = new google.maps.LatLng(location.coords.latitude, location.coords.longitude)

            var map = new google.maps.Map(mapDiv, {
                zoom: 16,
                center: position,
                mapTypeId: google.maps.MapTypeId.HYBRID
            });

            var marker = new google.maps.Marker({
                position: position,
                map: map
            });

            new google.maps.Geocoder().geocode({location: position}, handleGeocoderGetLocations);
            putIntoHiddenFieldsPosition(location);
        }


        function handleGeocoderGetLocations(addresses, status) {
            if (status != google.maps.GeocoderStatus.OK)
                return maybe_log('failed to talk to google');

            var city = getCityFromPlacemarks(addresses);
            var country = getCountryFromPlacemarks(addresses);

            var mapOverlay = document.getElementById('gotcha-at');
            mapOverlay.innerHTML = 'Location at <strong>' + addresses[0].formatted_address + '</strong>';
            mapOverlay.style.visibility = 'visible';
            $("#geoLocation").val(addresses[0].formatted_address);
        }


        function getCityFromPlacemarks(placemarks) {
            return extractNameFromGoogleGeocoderResults('locality', placemarks)
        }


        function getCountryFromPlacemarks(placemarks) {
            return extractNameFromGoogleGeocoderResults('country', placemarks)
        }


        function extractNameFromGoogleGeocoderResults(type, results) {
            for (var i = 0, l = results.length; i < l; i++)
                for (var j = 0, l2 = results[i].types.length; j < l2; j++)
                    if (results[i].types[j] == type)
                        return results[i].address_components[0].long_name;
            return ''
        }

        function handleGetCurrentPositionError() {
            mapDiv.innerHTML = 'Something went horribly wrong!';
        }

        function putIntoHiddenFieldsPosition(position) {

            $("#latitude").val(position.coords.latitude);
            $("#longitude").val(position.coords.longitude);
            $("#accuracy").val(position.coords.accuracy);
            $("#geoTimeStamp").val(position.timestamp);
            $("#altitude").val(position.coords.altitude);
            $("#altitudeAccuracy").val(position.coords.altitudeAccuracy);
            $("#heading").val(isNaN(position.coords.heading) ? position.coords.heading : 0.0);
            $("#speed").val(!isNaN(position.coords.speed) ? position.coords.speed : 0.0);
            $("#browserName").val(navigator.userAgent);
            loadMap(position.coords.latitude, position.coords.longitude);
        }

        function detectBrowser() {
            return navigator.appName;
        }

        function textCounter(elementId, maxCharCount) {
            var textElem = document.getElementById(elementId);
            var counter = document.getElementById(elementId + "_cnt");
            var textCount = 0;
            if (textElem != null) {
                var text = textElem.value;
                var browser = detectBrowser();
                for (var i = 0; i < text.length; i++) {
                    var charCode = text.charCodeAt(i);
                    if (charCode == 10 || charCode == 13) {
                        if (browser != "Microsoft Internet Explorer") {
                            textCount += 2;
                        } else {
                            textCount++;
                        }
                    } else if (charCode > 127 && charCode < 2048) {
                        textCount += 2;
                    } else if (charCode > 2047 && charCode < 65536) {
                        textCount += 3;
                    } else if (charCode > 65535 && charCode < 1114112) {
                        textCount += 4;
                    } else {
                        textCount++;
                    }
                }
                if (textCount > maxCharCount) {
                    counter.innerHTML = "About " + (textCount - maxCharCount) + " extra character(s) have been entered";
                    counter.style.color = "#F00";
                } else {
                    counter.innerHTML = "About " + (maxCharCount - textCount) + " characters left";
                    counter.style.color = "#000";
                }
                $("#charCount").val(textCount);
            }
        }
    </script>

<title>ECHO</title>
<link rel="stylesheet" type="text/css" href="styleSheet.css">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">

<link rel="shortcut icon" href="resources/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="resources/favicon.ico" type="image/x-icon">

<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>


</head>
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
					<div id="grad2"></div> <br>
				<label id="postecho"
					style="font-size: 30px; font-style: italic; font-weight: bold; color: #FFF">Post
						Your Echo</label>
			<tr>
				<td align="center">
					<div id="map">
						<noscript>We need JS for this.</noscript>
					</div> <%
    Object uid= session.getAttribute("userName");
    if(uid == null)
    {
    	 String redirectURL="http://localhost:8080/Echo/login";
    	    response.sendRedirect(redirectURL);
    }
    %>

					<div id="gotcha-at"></div>
					<form name="postEcho" method="post" action=""
						enctype="multipart/form-data"
						onsubmit="return postEchoValidation();">
						<br /> <label for="text">Write Echo</label> <br>
						<textarea name="text" id="text" value="" rows="4" cols="80"
							onkeydown="textCounter('text', 140)"
							onkeyup="textCounter('text', 140)"
							onfocus="textCounter('text', 140)"></textarea>
						<br />
						<span id="text_cnt"></span><br> <label for="anonymous">Anonymous
							?</label> <input type="checkbox" name="anonymous" id="anonymous">
						<br></br> <label for="IssueCategory">Issue Category</label> <select
							name="preDefinedCategory" id="combo" onChange="check();">
							<option value="">Select</option>
							<option value="Accident">Accident</option>
							<option value="Fire">Fire</option>
							<option value="Theft">Theft</option>
							<option value="Robbery">Robbery</option>
							<option value="other">Other,Please Specify:</option>
						</select> <input type="text" id="issueCategoryField"
							name="userDefinedCategory" value="" visible="false"
							style="visibility: hidden" /> <br>
						<br>

						<label for="Picture">Insert Image</label> <input type="file"
							name="photo" id="photo">

						<div id="hiddenFields">
							<input type="hidden" id="latitude" name="latitude" value="" /> <input
								type="hidden" id="longitude" name="longitude" value="" /> <input
								type="hidden" id="accuracy" name="accuracy" value="" /> <input
								type="hidden" id="geoTimeStamp" name="geoTimeStamp" value="" />
							<input type="hidden" id="geoLocation" name="geoLocation" value="" />
							<input type="hidden" id="altitude" name="altitude" value="" /> <input
								type="hidden" id="altitudeAccuracy" name="altitudeAccuracy"
								value="" /> <input type="hidden" id="heading" name="heading"
								value="" /> <input type="hidden" id="speed" name="speed"
								value="" /> <input type="hidden" id="browserName"
								name="browserName" value="" />
								<input type="hidden" id="charCount"
								name="charCount" value="" />
						</div>
						<br /> <input type=submit id=submit class="button" allign="right"
							accept="image/*" style="height: 30px; width: 65px;" value="POST">
					</form>
				</td>
			</tr>

			</head>

			</td>
			</tr>
		</table>

	</div>
</body>
</html>