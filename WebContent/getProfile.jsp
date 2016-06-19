<%@page import="Models.DBObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
profile id is: 
<%
	DBObject db = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
	
out.print(request.getParameter("id"));

%>