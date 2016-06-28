<%@page import="Models.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script>
    	function ajaxSearch(type) {
    		var url = "";
    		if (type == 1) {
    			var val = document.getElementById("ajaxUserSearch").value;
    			if (val == "") {
    				document.getElementById("ajaxResult").innerHTML = "";
    				return;
    			}
    			url = 'ajaxUserSearch.jsp?<%=Constants.AJAX_USER_SEARCH%>='+ val;
    		} else if (type == 2) {
    			var val = document.getElementById("ajaxQuizSearch").value;
    			if (val == "") {
    				document.getElementById("ajaxResult").innerHTML = "";
    				return;
    			}
    			url = 'ajaxQuizSearch.jsp?<%=Constants.AJAX_QUIZ_SEARCH%>='+ val;
    		} else
    			return;
    		var xhttp = new XMLHttpRequest();
    		xhttp.onreadystatechange = function() {
    			if (xhttp.readyState == 4 && xhttp.status == 200) 
    	    		document.getElementById("ajaxResult").innerHTML = xhttp.responseText;
    		};
    		xhttp.open('GET', url, true);
		xhttp.send();
	}
</script>
<div class="block-header" id="">Search Users/Quizes</div>
<div class="block" id="">
	<input type="text" placeholder="Enter username" id="ajaxUserSearch"
		onkeyup="ajaxSearch(1)" />
	<input type="text" placeholder="Enter Quiz Name" id="ajaxQuizSearch"
		onkeyup="ajaxSearch(2)" />
	<div id="ajaxResult"></div>
</div>

<div class="block-header" id="">block2</div>
<div class="block" id="">block content 2 right</div>

<div class="block-header" id="">block3</div>
<div class="block" id="">block content 3 right</div>

<div class="block-header" id="">block4</div>
<div class="block" id="">block content 4 right</div>