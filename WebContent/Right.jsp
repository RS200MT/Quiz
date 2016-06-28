<%@page import="Models.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <script>
    	function ajaxSearch() {
    		var val = document.getElementById("ajaxUserSearch").value;
    		if (val == "") {
    			document.getElementById("ajaxResult").innerHTML = "";
    			return;
    		}
    		var xhttp = new XMLHttpRequest();
    		xhttp.onreadystatechange = function() {
    			if (xhttp.readyState == 4 && xhttp.status == 200) 
    	    		document.getElementById("ajaxResult").innerHTML = xhttp.responseText;
    		};
    		xhttp.open('GET', 'ajaxUserSearch.jsp?<%=Constants.AJAX_USER_SEARCH%>=' + val, true);
    		xhttp.send();
    	}
    </script>
<div class="block-header" id="">Search Users</div>
<div class="block" id=""><input type="text" placeholder="Enter username" id="ajaxUserSearch" onkeyup="ajaxSearch()"/><div
		id="ajaxResult"></div>
</div>

<div class="block-header" id="">block2</div>
<div class="block" id="">block content 2 right</div>

<div class="block-header" id="">block3</div>
<div class="block" id="">block content 3 right</div>

<div class="block-header" id="">block4</div>
<div class="block" id="">block content 4 right</div>