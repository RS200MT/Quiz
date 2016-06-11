<%@page import="Models.Quiz"%>
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
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
		Quiz quiz = null;
		Object passedId = request.getParameter(Constants.QUIZ_PAGE_ID);
		if (passedId != null) {
			int quizId = (int) passedId;
			DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
			quiz = obj.getQuizById(quizId);
		} else {
			quiz = (Quiz) request.getSession().getAttribute(Constants.ATTR_SESSION_QUIZ);
			if (quiz == null) {
				// sisulele iyo.
			}
		}

		if (quiz.isSingleQuestion()) {
			//single question
		} else {
			//all quiz on page
		}
	%>
</body>
</html>