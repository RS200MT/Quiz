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

<%
	User currUser = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	String userName = "";
	User toDisplay = null;
	DBObject db = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
	if(currUser == null) {
		out.print("You must log in to display user's profile.");
	} else {
		userName = request.getParameter(Constants.ATTR_USER_NAME_FOR_GET_PROFILE);
		toDisplay = db.getUserByUserName(userName);
		out.print(userName);
		out.print("<br>"+toDisplay.getEmail()+"<br>");
		ArrayList<Pair<String, Integer>> quizesByUser = db.getQuizesListForUser(toDisplay.getId());
		if(quizesByUser != null) {
			out.print("Number of quizes created :" + quizesByUser.size()+"<br>");
			for(Pair<String, Integer> q : quizesByUser) {
				out.print("<a href = '" + Constants.getQuizURL(q.getValue()) + "'>" + q.getKey() + "</a> <br>");
			}
		}
	}
		
%>


<form action= "AddFriend" method="post">
<% out.print("<input type='hidden' name='"+Constants.GET_PROFILE_USER_NAME_HIDDEN+"' value='"+userName+"'/>");
	if(currUser == null) {
		out.print("<H3> Log in to countinue </H3>");
	} else {
		if(!currUser.getUserName().equals(userName)) {
			if(!db.usersAreFriends(currUser.getId(), toDisplay.getId())) {
				ArrayList<String> friendRequests = db.getFriendRequestsForUser(currUser.getId());
				if(friendRequests != null && friendRequests.contains(userName)) {
					out.print("<input type='submit' name='"+Constants.GET_PROFILE_ACCEPT_FRIEND_REQUEST+"' value = 'Accept Friend Request'/>");
					out.print("<input type='submit' name='"+Constants.GET_PROFILE_DECLINE_FRIEND_REQUEST+"' value = 'Decline Friend Request'/>");
					//System.out.println(userName+" has sent request to "+currUser.getUserName());
				} else {
					ArrayList<String> friendRequestsForToDisplay = db.getFriendRequestsForUser(toDisplay.getId());
					if(friendRequestsForToDisplay!= null && friendRequestsForToDisplay.contains(currUser.getUserName())) {
						out.print("<input type='submit' name='"+Constants.GET_PROFILE_UNFRIEND+"' value = 'Unfriend'/>");
						//System.out.println(currUser.getUserName()+" has sent request to "+ userName);
					} else {
						out.print("<input type='submit' name='"+Constants.GET_PROFILE_ADD_FRIEND+"' value = 'Add Friend'/>");
						//System.out.println(currUser.getUserName()+" and "+userName+" have not sent requests to each other.");
					}
				}
			} else {
				out.print("<input type='submit' name='"+Constants.GET_PROFILE_UNFRIEND+"' value = 'Unfriend'/>");
			}
		}
	}


%>
</form>



<form action= "<%=Constants.S_SEND_MESSAGE%>" method="post">
<% out.print("<input type='hidden' name='"+Constants.GET_PROFILE_USER_NAME_HIDDEN+"' value='"+userName+"'/>");
	if(currUser == null) {
		out.print("<H3>Log in to countinue</H3>");
	} else {
		if(!currUser.getUserName().equals(userName)) {
			out.print("<br>Message Text here");
			out.print("<br><input type='text' name='"+Constants.GET_PROFILE_MESSAGE_TEXT+"'/>");
			out.print("<input type='submit' name='"+Constants.GET_PROFILE_SEND_MESSAGE+"' value = 'Send Message'/>");
		}
	}
	 
%>
</form>






