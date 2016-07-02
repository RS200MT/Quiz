<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
    
    
    
<%
    
	DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	ArrayList<String> friends = obj.getUserFriendsById(user.getId());
	if(friends != null){
		for(String s : friends){
			out.print("<a href ='" + Constants.getUserProfileURL(s) + "'>" + s +"</a><br>");
		}
	}
%>