<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
</head>
<body>
	<%
		String incorrectLogin = (String)request.getAttribute(Constants.ATTR_FAILED_LOGIN);
		User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		if (curUser == null) {
			if (incorrectLogin != null)
				out.println(incorrectLogin);
	%>
	<form action="<% out.print(Constants.S_LOGIN); %>" method="post">
		<p>
			Username: <input type="text" required="required" name="<% out.print(Constants.LOGIN_USERNAME); %>" id="<% out.print(Constants.LOGIN_USERNAME); %>" />
		<p>
			Password: <input type="password" required="required" name="<% out.print(Constants.LOGIN_PASSWORD); %>" id="<% out.print(Constants.LOGIN_PASSWORD); %>" />
		<p>
			<input type="submit" value="<% out.print(Constants.B_LOGIN); %>">
	</form>
	<a href="<% out.print(Constants.P_REGISTER); %>"> Create New Account</a>
	<%
		} else {
			request.getRequestDispatcher(Constants.P_HOMEPAGE).forward(request, response);
		}
	%>
</body>
</html>