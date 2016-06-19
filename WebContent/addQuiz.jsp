<%@page import="java.util.ArrayList"%>
<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
	if (curUser != null) {
%>
<form action="<%=Constants.S_ADD_QUIZ%>" method="post">
	<p>
		Quiz title: <input type="text" required="required"
			name="<%=Constants.ADD_QUIZ_TITLE%>"
			id="<%=Constants.ADD_QUIZ_TITLE%>" />
	</p>
	<p>
		Quiz Description: <input type="text" required="required"
			name="<%=Constants.ADD_QUIZ_DESCRIPTION%>"
			id="<%=Constants.ADD_QUIZ_DESCRIPTION%>" />
	</p>
	<p>
		Randomize questions: <input type="checkbox" name="<%=Constants.ADD_QUIZ_RANDOMIZED%>" value="<%=Constants.ADD_QUIZ_RANDOMIZED%>">
	</p>
	<p>
		Immediate correction: <input type="checkbox" name="<%=Constants.ADD_QUIZ_IMMEDIATE_CORRECTION%>" value="<%=Constants.ADD_QUIZ_IMMEDIATE_CORRECTION%>">
	</p>
	<input type="submit" value="<%=Constants.B_ADD_QUIZ%>" />
</form>
<%
	} else {
%>
	Please log in to create a quiz!
<%
	}
%>
