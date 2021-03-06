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

<center>
<%
if(user != null) {
	String newMessages = "";
	ArrayList<Integer> unseenMessages = obj.getUnseenMessages(user.getId());
	if(unseenMessages!=null) {
		newMessages+="("+unseenMessages.size()+")";
	}
	%>
	<button onClick="window.location.href = 'index.jsp?do=inbox';" type="button" style='width:180px'>INBOX<%=newMessages%></button>
<% 
} 
%>


<%
if(user != null) { %>
	<button onClick="window.location.href = 'index.jsp?do=friends';" type="button" style='margin-top:10px; width:180px'>FRIENDS</button>
<%
} 
%>


<%
if(user != null) {
	String newFriendRequests = "";
	int numRequests = obj.getNumberOfFriendRequests(user.getId());
	if(numRequests > 0) {
		newFriendRequests += "("+numRequests+")"; %>
	<button onClick="window.location.href = 'index.jsp?do=friendRequests';" type="button" style='margin-top:10px; width:180px'>FRIEND REQUESTS<%=newFriendRequests%></button>
<%	
	}
}
%>


<%
if(user != null) {
	int numChallenges = obj.getNumberOfUnseenChallenges(user.getUserName());
	String newChallenges = "";
	if(numChallenges>0){
		newChallenges = "("+numChallenges+")";
	}%>
	<button onClick="window.location.href = 'index.jsp?do=challenges';" type="button" style='margin-top:10px; width:180px'>CHALLENGES<%=newChallenges%></button>

<%
}
%>
</center>


