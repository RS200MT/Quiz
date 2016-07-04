<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.User"%>
<%@page import="java.util.ArrayList"%>

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


<%
if(user != null) {
	out.print("<div class='btn-group'>");
	out.print("<center><button style='width:150px' type='button' class='btn btn-primary'>");//"<div class='block-header' id=''>");
	out.print("<H3>");
	String newMessages = "";
	ArrayList<Integer> unseenMessages = obj.getUnseenMessages(user.getId());
	if(unseenMessages!=null) {
		newMessages+="("+unseenMessages.size()+")";
}
out.print("<a href ='" + Constants.getAction("inbox") + "'><font color = '#12273b'>INBOX "+newMessages+"</a> </H3>" );
out.print("</button>");
} 

%>

<%
if(user != null) {
	out.print("<center><button style='width:150px' type='button' class='btn btn-primary'>");
	out.print("<a href ="+Constants.getAction(Constants.INDEX_DO_FRIEND)+"><font color = '#12273b'><H3>Friends</H3></a>");
	out.print("</button>");
} 
%>



<%
if(user != null) {
	String newFriendRequests = "";
	int numRequests = obj.getNumberOfFriendRequests(user.getId());
	if(numRequests > 0) {
		out.print("<center><button style='width:150px' type='button' class='btn btn-primary'>");
		out.print("<H3>");
		newFriendRequests += "("+numRequests+")";
		out.print("<br><a href ="+Constants.getAction(Constants.INDEX_DO_FRIEND_REQUESTS)+"><font color = '#12273b'>Friend Requests"+newFriendRequests+"</a></H3>");
		out.print("</button>");
	}
} 

%>


<%
if(user != null) {
	int numChallenges = obj.getNumberOfUnseenChallenges(user.getUserName());
	if(numChallenges > 0) {
		out.print("<center><button style='width:150px' type='button' class='btn btn-primary'>");
		String newChallenges = "("+numChallenges+")";
		out.print("<br><a href="+Constants.getAction(Constants.INDEX_DO_CHALLENGES)+"><font color = '#12273b'><H3>Challenges"+newChallenges+"</H3></a>");
		out.print("</button>");
	}
}
out.print("</div>");
%>

