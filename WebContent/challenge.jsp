<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Constants"%>
<%@page import="Models.Quiz"%>
    
<% 
	DBObject db = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
    int quizId = Integer.parseInt(request.getParameter("quizID"));
	String recipient = (String) request.getParameter(Constants.AJAX_USER_SEARCH);
	int sender_ID =  Integer.parseInt(request.getParameter("senderID"));
	String sender = db.getUserNameById(sender_ID);
	if(db.addChallenge(sender, recipient, quizId)){
		out.print("Challenge sent successfully.");
	} else {
		out.print("Could not send challenge.");
	}
%>

 <%out.print("<br><a href ='" + Constants.getQuizURL(quizId)+"'> Return to quizPage</a>" ) ;%> 