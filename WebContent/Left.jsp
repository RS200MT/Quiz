<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	boolean loggedIn = ((User) request.getSession().getAttribute(Constants.ATTR_USER)) != null;
	if (loggedIn) {
%>
<div class="block-header" id="profileInfo">Profile</div>
<div class="block" id="profileInfo">
	<%
		String profileInfo = Constants.INDEX_DO_PROFILE_INFO + ".jsp";
	%>
	<jsp:include page="<%=profileInfo%>" />
</div>
<%
	}
%>
<div class="block-header" id="">Popular quizzes</div>
<div class="block" id="">
<%

	DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
		ArrayList<Pair<String, Integer>> popQuizes = obj.getPopularQuizes(5);
		if(popQuizes != null) {
			for (int i = 0; i < popQuizes.size(); i++) {
				Pair<String, Integer> q = popQuizes.get(i);
				out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
			}
		} else {
			out.print("There are no quizzes yet...");
		}
%>

</div>

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


<%
if(user != null) {
	ArrayList<Pair<String,Integer>> recentQuizesForUser = obj.getRecentQuizesForUser(user.getId(), 5);
	if(recentQuizesForUser != null){
		out.print("<div class='block-header' id=''>Recent quizzes taken by you : <br></div>");
		out.print("<div class='block' id=''>");
		for(Pair<String,Integer> q : recentQuizesForUser){
			out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");	
		}
		out.print("</div>");
	}
}

%>

