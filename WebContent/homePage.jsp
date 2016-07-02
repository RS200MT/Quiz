<%@page import="Models.Constants"%>
<%@page import="Models.Message"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%

	DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	
%>


	
