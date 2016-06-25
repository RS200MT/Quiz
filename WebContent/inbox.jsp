<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="Models.Message"%>

    
    <% 
     	DBObject obj = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
   	 	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
   	 	ArrayList<Message> messages = obj.getMessages(user.getId());
    	 	if(messages != null){
 			for(Message m : messages){
 				out.print("From : <a href = message.jsp?messageId=" +m.getMessageId()+ "> "+obj.getUserNameById(m.getSenderId()) +"</a>");
 			}
 		} 
    %>