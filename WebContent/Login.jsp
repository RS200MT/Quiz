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
User curUser = (User)request.getSession().getAttribute(User.USER_ATTR);
if (curUser != null) {
	out.println("You are already logged as: <b>" + curUser.getUserName() + "</b>");
}
%>
<form action="Login" method="post">
<p>Username: <input type="text" name="username">
<p>Password: <input type="password" name ="password">
<p> <input type="submit" value="Login">
</form>
<a href ="newAccount.jsp"> Create New Account</a>
</body>
</html>