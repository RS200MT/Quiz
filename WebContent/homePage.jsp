<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<H3> Popular Quizes : <br> </H3>
<%
DBObject obj = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
ArrayList<Quiz> popQuizes = obj.getPopularQuizes(2);
for(Quiz q : popQuizes){
	out.print("<a href = '" + Constants.getQuizURL(q.getID()) + "'>" + q.getTitle() + "</a> <br>");
}

%>

<H3> Recent Quizes : <br> </H3>
<%
ArrayList<Quiz> recentQuizes = obj.getRecentQuizes(3);
for(Quiz q : recentQuizes){
	out.print("<a href = '" + Constants.getQuizURL(q.getID()) + "'>" + q.getTitle() + "</a> <br>");
}

%>

<H3> Recent Quizes For user : <br> </H3>
<%
User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
ArrayList<Quiz> recentQuizesForUser = obj.getRecentQuizesForUser(user.getId(),3);
if(recentQuizesForUser != null){
	for(Quiz q : recentQuizesForUser){
		out.print("<a href = '" + Constants.getQuizURL(q.getID()) + "'>" + q.getTitle() + "</a> <br>");
	}
}

%>