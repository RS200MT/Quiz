<%@page import="Models.Constants"%>
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
	<%
		String alreadyExists = (String) request.getAttribute(Constants.ATTR_USERNAME_EXISTS);
		User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		if (curUser == null) {
			if (alreadyExists != null)
				out.println(alreadyExists);
	%>
	<form action="<%out.print(Constants.S_REGISTER);%>" method="post">
		<p>
			UserName: <input type="text" required="required"
				name="<%out.print(Constants.REGISTER_USERNAME);%>"
				id="<%out.print(Constants.REGISTER_USERNAME);%>" />
		<p>
			Email: <input type="email" required="required"
				name="<%out.print(Constants.REGISTER_EMAIL);%>"
				id="<%out.print(Constants.REGISTER_EMAIL);%>" />
		<p>
			Password: <input type="password" required="required"
				name="<%out.print(Constants.REGISTER_PASSWORD);%>"
				id="<%out.print(Constants.REGISTER_PASSWORD);%>" />
		</p>
		<p>
			<input type="submit" value="<%out.print(Constants.B_REGISTER);%>" />
		</p>
	</form>
	<%
		} else {
			request.getRequestDispatcher(Constants.P_HOMEPAGE).forward(request, response);
		}
	%>
</body>
</html>