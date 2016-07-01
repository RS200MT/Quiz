<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.Challenge"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%  
	DBObject db = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User currUser = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	ArrayList<Challenge> challenges = db.getChallengesForUser(currUser.getId());
	if(challenges != null) {
		for(Challenge c: challenges) {
			if(!c.isSeen()) {
				String senderName = db.getUserNameById(c.getSenderId());
				String quizTitle = db.getQuizById(c.getQuizId(), 0).getTitle();
				int bestScore = db.getBestScoreForUserInQuiz(c.getSenderId(), c.getQuizId());
				out.print("<br> <a href ="+Constants.getUserProfileURL(senderName)+"> "+senderName+"</a> challenged you to take quiz "+
								"<a href ="+Constants.getQuizURL(c.getId())+"> "+quizTitle+"</a> . Their best score is "+bestScore+".");
			}
		}
	} else {
		out.print("<H3> You have no new challenges.</H3>");
	}
	
%>
