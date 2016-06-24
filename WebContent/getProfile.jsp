<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<form action=<%=Constants.S_GET_PROFILE%> method="post">
 
<%
	User currUser = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	if(currUser == null) {
		out.print("You must log in to display user's profile.");
	} else {
		String userName = request.getParameter(Constants.ATTR_USER_NAME_FOR_GET_PROFILE);
		DBObject db = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
		User toDisplay = db.getUserByUserName(userName);
		out.print("<h1>"+ userName+ " </h1>");
		out.print("<h2>"+ toDisplay.getEmail() + "</h2>");
		out.print("<input type='hidden' name='"+Constants.GET_PROFILE_USER_NAME_HIDDEN+"' value='"+userName+"'/>");
		if(!currUser.getUserName().equals(userName)) {
			if(!currUser.hasFriendByUserName(userName)) {
				out.print("<input type='submit' name='"+Constants.GET_PROFILE_ADD_FRIEND+"' value = 'Add Friend'/>");	
			}
			out.print("<br><input type='text' name='"+Constants.GET_PROFILE_MESSAGE_TEXT+"' value = 'Message Text'/>");
			out.print("<input type='submit' name='"+Constants.GET_PROFILE_SEND_MESSAGE+"' value = 'Send Message'/>");
		} else {
			//Unfriend
		}
	}
	
%>
</form>