<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addUser" method="post">
<%
Object exists = request.getAttribute("exists");
if (exists != null && exists.toString().equals("1"))
	out.println("UserName <b>" + request.getParameter("username") + "</b> or email <b>" + request.getParameter("email") +"</b> already exists!");
else {
	User curUser = (User)request.getSession().getAttribute(User.USER_ATTR);
	if (curUser != null)
		out.println("You are already logged as: <b>" + curUser.getUserName() + "</b>");
}
%>
<p>UserName: <input type="text" name="username"/>
<p>Email: <input type="email"  name="email"/>
<p>Password: <input type="password" name="password"/>
<p> <input type="submit" value="Sign Up"/>
</form>
</body>
</html>