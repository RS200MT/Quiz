<%@page import="Models.Constants"%>
<%@page import="Models.Quiz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_MESSAGE) != null) {
	out.print(request.getAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_MESSAGE));
}


%>