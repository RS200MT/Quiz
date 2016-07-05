<%@page import="Models.Constants"%>
<%@page import="Models.Message"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);

	//Display quizes created by friends;
	for(String f: user.getFriends()) {
		ArrayList<Pair<Integer, String>> quizesCreatedByFriends = obj.getRecentQuizesCreatedBy(f,5);
		if(quizesCreatedByFriends!=null) {
			for(Pair<Integer, String> p: quizesCreatedByFriends) {
				String toPrint = "<br><a href='"+Constants.getUserProfileURL(f)+"'>"+f+"</a> created quiz "+
								"<a href='"+Constants.getQuizURL(p.getKey())+"'>"+p.getValue()+"</a>.";
				out.print(toPrint);
			}
		}		
	}
	
	//Display friends' recent quiz-taking activity
	for(String f: user.getFriends()) {
		ArrayList<Pair<Quiz, Integer>> recentQuizesTakenByFriend = obj.getRecentQuizesTakenBy(f, 3);
		if(recentQuizesTakenByFriend != null) {
			for(Pair<Quiz, Integer> p: recentQuizesTakenByFriend) {
				out.print("<br><a href='"+Constants.getUserProfileURL(f)+"'>"+f+"</a> took quiz "+
						"<a href='"+Constants.getQuizURL(p.getKey().getID())+"'>"+p.getKey().getTitle()+". </a> and got score "+
						p.getValue()+" out of "+p.getKey().getMaxScore()+".");
			}
		}
	}
	
%>


	
