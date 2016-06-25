<%@page import="Models.DBObject"%>
<%@page import="Models.Message" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="Models.Message"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	DBObject obj = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	Message m = obj.getMessageById(Integer.parseInt(request.getParameter("messageId")));
	if(m!=null){
		String senderUserName = obj.getUserNameById(m.getSenderId());
		out.print("From: " + "<a href = '" + Constants.getUserProfileURL(senderUserName)+"'>"+senderUserName+"</a>");				
		out.print("<br>Time: "+m.getReceiveTime()+"<br>");
		out.print("<br>"+m.getMessageText());
	}
	
%>
</body>
</html>