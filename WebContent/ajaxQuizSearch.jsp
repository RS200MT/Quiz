<%@page import="javafx.util.Pair"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String quizname = (String) request.getParameter(Constants.AJAX_QUIZ_SEARCH);
	if (quizname == null)
		out.print("Invalid arguments!");
	else {
		DBObject db = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
		ArrayList<Pair<Integer, String>> quizes = db.getQuizesStartedWith(quizname, 5);
		if (quizes.size() == 0)
			out.print("There are no Quizes started by: <b>" + quizname + "</b>");
		for (int i = 0; i < quizes.size(); i++) {
			String curQuizTitle = quizes.get(i).getValue();
			int id = quizes.get(i).getKey();
			out.print("<a href='" + Constants.getQuizURL(id)
					+ "' target='_blank'><div class='ajaxSearchResult'>" + curQuizTitle + "</div></a>");
		}
	}
%>