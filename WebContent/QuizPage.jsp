<%@page import="Models.Question"%>
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
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		int quizId = Integer.parseInt(request.getParameter(Constants.ATTR_QUIZ_ID_FOR_QUESTION));
		Quiz quiz = (Quiz) request.getSession().getAttribute(Constants.ATTR_SESSION_QUIZ);
		if (quiz == null || quiz.getID() != quizId) {
	%>
	<script>
		function startQuiz() {
			document.getElementById("singleQuestionForm").style.display = "inline";
			document.getElementById("summaryForQuiz").style.display = "none";
			return false;
		}
	</script>
	<div id="summaryForQuiz" id="summaryForQuiz">
		<%
			out.print(obj.getSummaryForQuiz(quizId));
		%>
		<BR>
		<button onClick="return startQuiz();">Start Quiz</button>
	</div>
	<div id="singleQuestionForm" style="display: none">
		<p>
			<input type="radio" name="<%=Constants.QUIZINT_SINGLE_QUESTION%>"
				value="1" />One question on page
		</p>
		<p>
			<input type="radio" name="<%=Constants.QUIZINT_SINGLE_QUESTION%>"
				value="2" />All questions on page
		</p>
		<input type="submit" value="Submit" />
	</div>
	<%
		} else {
			String checkAnswer = "Check Answer";
			String nextQuestion = "Next Question";
			String doneQuiz = "Done Quiz";
			out.print(quiz.getHTML());
			if (quiz.displaySingleQuestion()) {
				if (quiz.isImmediateCorrection()) {
					out.print("<input type='submit' name='"
							+ (quiz.isLastQuestion() ? Constants.QUIZINIG_NEXT : Constants.QUIZINIG_CHECK)
							+ "' value='check answer'/>");
				} else {
					out.print(
							"<input type='submit' name='" + Constants.QUIZINIG_NEXT + "' value='next question'/>");
				}
			} else {
				out.print("<input type='submit' name='" + Constants.QUIZINIG_DONE + "' value='done quiz'/>");
			}
		}
	%>
	<input type="hidden" name="<%=Constants.ATTR_QUIZ_ID_FOR_QUESTION%>"
		value="<%=quizId%>" />
</form>