<%@page import="Models.DBObject"%>
<%@ page import="java.util.ArrayList" %>
<%@page import="Models.Quiz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello user</title>
</head>
<body>
	<% 
		out.print(request.getParameter("username"));
		//ES YVELAFERI JER AR IMUSHAVEBS :D
// 		DBObject db = (DBObject)getServletContext().getAttribute("DB");
// 		ArrayList<Quiz> popular = db.getPopularQuizes(3);
// 		for(Quiz q: popular) {
// 			out.println("<li><a href=\"QuizPage.jsp?id=" + q.getID()+ "\">" + q.getAuthor()+ "</a></li>");
// 		}
// 		ArrayList<Quiz> recent = db.getRecentQuizes(3);
// 		for(Quiz q: recent) {
// 			out.println("<li><a href=\"QuizPage.jsp?id=" + q.getID()+ "\">" + q.getAuthor()+ "</a></li>");
// 		}
	%>
</body>
</html>