<%@page import="Questions.Question"%>
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
		if (Question.QuestionType.QuestionResponse == 1)
			System.out.print("shemovida");
		User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		if (curUser == null) {
			request.getRequestDispatcher(Constants.P_LOGIN).forward(request, response);
		} else {
			out.print(curUser.getUserName());
	%>
	: Hello Homepage Here 
	<form action="<% out.print(Constants.S_LOGOUT); %>" method="post">
		<input type="submit" value="<% out.print(Constants.B_LOGOUT); %>" />
	</form> 
	<form action="<% out.print(Constants.P_ADD_QUIZ); %>" method="post">
		<input type="submit" value="<% out.print(Constants.B_ADD_QUIZ); %>" />
	</form>
	<%
		}
	%>
</body>
</html>