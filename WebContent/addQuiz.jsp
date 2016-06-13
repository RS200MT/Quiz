<%@page import="java.util.ArrayList"%>
<%@page import="Questions.Question"%>
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
	<input type="submit" value="<%=Constants.B_ADD_QUIZ%>" />
</form>
<%
	} else {
%>
	Please log in to create a quiz!
<%
	}
%>
