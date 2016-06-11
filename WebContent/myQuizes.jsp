<%@page import="javafx.util.Pair"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
	if (curUser == null) {
		out.print("You must be logged in to display your quizes");
		return;
	}
	DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
	ArrayList<Pair<String, Integer>> quizes = obj.getQuizesListForUser(curUser.getId());
	if (quizes.size() == 0) {
		out.print("There are no quizes for the user.");
		return;
	}
	for (int i = 0; i < quizes.size(); i++) {
		Pair<String, Integer> curQuiz = quizes.get(i);
		out.println("<a href='"+ Constants.getQuizURL(curQuiz.getValue()) +"'>QUIZ: " + curQuiz.getKey() + " | ID:" + curQuiz.getValue() + "</a><HR>");
	}
%>