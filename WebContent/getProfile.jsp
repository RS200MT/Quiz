<%@page import="javafx.util.Pair"%>
<%@page import="java.util.ArrayList"%>
<%-- <%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%> --%>
<%@page import="javafx.util.Pair"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<form action= "<%=Constants.S_GET_PROFILE%>" method="post">
 
<%
	User currUser = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	if(currUser == null) {
		out.print("You must log in to display user's profile.");
	} else {
		String userName = request.getParameter(Constants.ATTR_USER_NAME_FOR_GET_PROFILE);
		DBObject db = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
		User toDisplay = db.getUserByUserName(userName);
		out.print(userName);
		out.print("<br>"+toDisplay.getEmail()+"<br>");
		ArrayList<Pair<String, Integer>> quizesByUser = db.getQuizesListForUser(toDisplay.getId());
		out.print("Number of quizes created :" + quizesByUser.size()+"<br>");
		for(Pair<String, Integer> q : quizesByUser) {
			out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
		}
		out.print("<input type='hidden' name='"+Constants.GET_PROFILE_USER_NAME_HIDDEN+"' value='"+userName+"'/>");
		if(!currUser.getUserName().equals(userName)) {
			if(!currUser.hasFriendByUserName(userName)) {
				ArrayList<String> requests = db.getFriendRequestsForUser(currUser.getId());
				if(requests!=null && requests.contains(toDisplay.getUserName())) {
					out.print("<input type='submit' name='"+Constants.GET_PROFILE_ACCEPT_FRIEND_REQUEST+"' value = 'Accept Friend Request'/>");
					out.print("<input type='submit' name='"+Constants.GET_PROFILE_DECLINE_FRIEND_REQUEST+"' value = 'Decline Friend Request'/>");
				} else {
					out.print("<input type='submit' name='"+Constants.GET_PROFILE_ADD_FRIEND+"' value = 'Add Friend'/>");
				}
					
			} else {
				out.print("<input type='submit' name='"+Constants.GET_PROFILE_UNFRIEND+"' value = 'Unfriend'/>");
			}
			out.print("<br><input type='text' name='"+Constants.GET_PROFILE_MESSAGE_TEXT+"' value = 'Message Text'/>");
			out.print("<input type='submit' name='"+Constants.GET_PROFILE_SEND_MESSAGE+"' value = 'Send Message'/>");
		} 
	}
	
%>
</form>