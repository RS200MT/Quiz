<%@page import="Models.Constants"%>
<%@page import="Models.Message"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<H3>
	Popular 5 quizzes: <br>
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

<H3>Recent quizzes : <br> </H3>

	<%

ArrayList<Pair<String,Integer>> recentQuizes = obj.getRecentQuizes(3);
if(recentQuizes != null){
	for(Pair<String,Integer> q : recentQuizes){
		out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
	}
}
%>



<H3>
	Recent quizzes taken by you : <br>
</H3>
<%

ArrayList<Pair<String,Integer>> recentQuizesForUser = obj.getRecentQuizesForUser(user.getId(), 3);
if(recentQuizesForUser != null){
	for(Pair<String,Integer> q : recentQuizesForUser){
		out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");	}
}
out.print("<H3>");
String newMessages = "";
ArrayList<Integer> unseenMessages = obj.getUnseenMessages(user.getId());
if(unseenMessages!=null) {
	newMessages+="("+unseenMessages.size()+")";
}
out.print("<a href =" + Constants.getAction("inbox") + ">INBOX "+newMessages+"</a>" );
String newFriendRequests = "";
int numRequests = obj.getNumberOfFriendRequests(user.getId());
if(numRequests > 0) {
	newFriendRequests += "("+numRequests+")";
	out.print("<br><a href ="+Constants.getAction(Constants.INDEX_DO_FRIEND_REQUESTS)+">Friend Requests"+newFriendRequests+"</a>");
}
out.print("<br><a href ="+Constants.getAction(Constants.INDEX_DO_FRIEND)+">Friends</a>");

int numChallenges = obj.getNumberOfUnseenChallenges(user.getId());
if(numChallenges > 0) {
	String newChallenges = "("+numChallenges+")";
	out.print("<br><a href="+Constants.getAction(Constants.INDEX_DO_CHALLENGES)+"> Challenges"+newChallenges+"</a>");
}
out.print("</H3>");
%>

	
