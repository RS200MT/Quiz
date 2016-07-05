<%@page import="java.util.ArrayList"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<title><%=Constants.INDEX_DO_FRIEND_REQUESTS_TITILE %></title>
<%
	DBObject db = (DBObject)getServletContext().getAttribute(DBObject.ATTR_DB);
	User sessionUser = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	ArrayList<String> friendRequests = db.getFriendRequestsForUser(sessionUser.getId());
	if(friendRequests == null) {
		out.print("<h1>   No pending friend requests. </h1>");
	} else {
		for(String name:friendRequests) {
			out.print("<a href = '" + Constants.getUserProfileURL(name)+"'>"+name+"</a><br>");
		}
	}
%>
