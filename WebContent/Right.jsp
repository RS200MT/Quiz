<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javafx.util.Pair"%>

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
<div class="block-header" id="">Search Users/Quizzes</div>
<div class="block" id="">
	<input type="text" placeholder="Enter username" id="ajaxUserSearch"
		onkeyup="ajaxSearch(1)" />
	<input type="text" placeholder="Enter Quiz Name" id="ajaxQuizSearch"
		onkeyup="ajaxSearch(2)" />
	<div id="ajaxResult"></div>
</div>

<%
DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
%>

<div class="block-header" id="">Recent quizzes : <br></div>
<div class="block" id="">
<%
	ArrayList<Pair<String,Integer>> recentQuizes = obj.getRecentQuizes(5);
	if(recentQuizes != null){
		for(Pair<String,Integer> q : recentQuizes){
			out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
		}
	} else {
		out.print("There are no quizzes yet...");
	}

%>

</div>

