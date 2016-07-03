<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Constants"%>
<%@page import="Models.Quiz"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int quizId = Integer.parseInt(request.getParameter("quizId"));
	String username = (String) request.getParameter(Constants.AJAX_USER_SEARCH);
	int userId =  Integer.parseInt(request.getParameter(Constants.AJAX_USER_ID));
	if (username == null)
		out.print("Invalid arguments!");
	else {
		DBObject db = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
		ArrayList<String> usernames = db.getFriendsStartedWith(username, 5,userId);
		if (usernames.size() == 0)
			out.print("There is no username containins: <b>" + username + "</b>");
		for (String curUserName : usernames) {
			out.print("<a href='" + Constants.getChallengeURL(curUserName, quizId,userId)
 			+ "'><div class='ajaxSearchResult'>" + curUserName + "</div></a><br>");
		}
	}
%>