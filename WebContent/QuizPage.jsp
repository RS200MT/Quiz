<%@page import="Questions.Question"%>
<%@page import="Models.User"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
	if (curUser == null) {
		out.print("You must be logged in to accept the quiz!");
		return;
	}
%>

<form action="<%=Constants.S_QUIZING%>" method="post">
	<%
		int quizId = Integer.parseInt(request.getParameter(Constants.ATTR_QUIZ_ID_FOR_QUESTION));
		Quiz quiz = (Quiz) request.getSession().getAttribute(Constants.ATTR_SESSION_QUIZ);
		if (quiz == null || quiz.getID() != quizId) {
	%>
	<p>
		<input type="radio" name="<%=Constants.QUIZINT_SINGLE_QUESTION%>"
			value="1" />One question on page
	</p>
	<p>
		<input type="radio" name="<%=Constants.QUIZINT_SINGLE_QUESTION%>"
			value="2" />All questions on page
	</p>

	<%
		} else if (!quiz.isSingleQuestion())
			out.print(quiz.toHTMLall());
		else
			out.print(quiz.toHTMLsingle());
	%>
	<input type="hidden" name="<%=Constants.ATTR_QUIZ_ID_FOR_QUESTION%>"
		value="<%=quizId%>" /> <input type="submit" value="Submit" />
</form>