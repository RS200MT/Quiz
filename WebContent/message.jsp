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
<form action= "ReplyMessage" method="post">
<%
// 	out.print("<input type='hidden' name='"+Constants.GET_PROFILE_USER_NAME_HIDDEN+"' value='"+userName+"'/>");
	DBObject obj = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
	int messageId=Integer.parseInt(request.getParameter("messageId"));
	Message m = obj.getMessageById(messageId);
	if(m!=null){
		obj.markMessageAsSeen(messageId);
		String senderUserName = obj.getUserNameById(m.getSenderId());
		out.print("From: " + "<a href = '" + Constants.getUserProfileURL(senderUserName)+"'>"+senderUserName+"</a>");				
		out.print("<br>Time: "+m.getReceiveTime()+"<br>");
		out.print("<br> <H4>"+m.getMessageText()+"</H4>");
		out.print("<br> <input type='text' name='"+Constants.MESSAGE_REPLY_TEXT+"' value ='' />");
		out.print("<br> <input type='submit' name='"+Constants.MESSAGE_REPLY+"' value='Reply'/>");
		out.print("<input type='hidden' name='"+Constants.MESSAGE_REPLY_TO+"' value = '"+m.getSenderId()+"'/>");
		out.print("<input type='hidden' name='messageId' value = '"+messageId+"'/>");
	}
	
%>
</form>
</body>
</html>