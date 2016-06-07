<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new Quiz</title>
</head>
<body>
	<%
		User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		if (curUser == null) {
			request.getRequestDispatcher(Constants.P_HOMEPAGE).forward(request, response);
		} else {
	%>
	<form action="addQuiz" method="post">
		<input type="text" required="required" name="q_title" id="q_title" />
	</form>
	<%
		}
	%>

</body>
</html>