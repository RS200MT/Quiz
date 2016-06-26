<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<H3>
	Popular 5 Quizes: <br>
</H3>

<%

	DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	ArrayList<Pair<String, Integer>> popQuizes = obj.getPopularQuizes(5);
	for (int i = 0; i < popQuizes.size(); i++) {
		Pair<String, Integer> q = popQuizes.get(i);
		out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
	}
	if (popQuizes.size() == 0)
		out.print("There are no quizes..");
	
%>

<H3>Recent Quizes : <br> </H3>

	<%

ArrayList<Pair<String,Integer>> recentQuizes = obj.getRecentQuizes(3);
if(recentQuizes != null){
	for(Pair<String,Integer> q : recentQuizes){
		out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
	}
}
%>



<H3>
	Recent Quizes For user : <br>
</H3>
<%

ArrayList<Pair<String,Integer>> recentQuizesForUser = obj.getRecentQuizesForUser(user.getId(), 3);
if(recentQuizesForUser != null){
	for(Pair<String,Integer> q : recentQuizesForUser){
		out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");	}
}
out.print("<H3>");
out.print("<a href =" + Constants.getAction("inbox") + ">INBOX</a>" );
out.print("<br><a href ="+Constants.getAction(Constants.INDEX_DO_FRIEND_REQUESTS)+">Friend Requests</a>");
out.print("<br><a href ="+Constants.getAction(Constants.INDEX_DO_FRIEND)+">Friends</a>");
out.print("</H3>");
%>

	
