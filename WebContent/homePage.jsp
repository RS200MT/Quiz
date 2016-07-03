<%@page import="Models.Constants"%>
<%@page import="Models.Message"%>
<%@page import="Models.DBObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="javafx.util.Pair"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%

	DBObject obj = (DBObject) request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	for(String f: user.getFriends()) {
		ArrayList<Pair<Integer, String>> quizesCreatedByFriends = obj.getRecentQuizesCreatedBy(f,4);
		if(quizesCreatedByFriends!=null) {
			for(Pair<Integer, String> p: quizesCreatedByFriends) {
				String toPrint = "<a href='"+Constants.getUserProfileURL(f)+"'>"+f+"</a> created quiz "+
								"<a href='"+Constants.getQuizURL(p.getKey())+"'>"+p.getValue()+"</a>. <br>";
				out.print(toPrint);
			}
		}
	}
	
%>


	
