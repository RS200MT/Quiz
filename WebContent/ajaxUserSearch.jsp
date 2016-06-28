<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String username = (String) request.getParameter(Constants.AJAX_USER_SEARCH);
	if (username == null)
		out.print("Invalid arguments!");
	else {
		DBObject db = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
		ArrayList<String> usernames = db.getUsersStartedWith(username, 5);
		if (usernames.size() == 0)
			out.print("There are no usernames started by: <b>" + username + "</b>");
		for (int i = 0; i < usernames.size(); i++) {
			String curUserName = usernames.get(i);
			out.print("<a href='" + Constants.getUserProfileURL(curUserName)
					+ "' target='_blank'><div class='ajaxSearchResult'>" + curUserName + "</div></a>");
		}
	}
%>