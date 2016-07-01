<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.Constants"%>
<%@page import="Models.DBObject"%>
<%@page import="Models.Quiz"%>
<%@page import="Models.User"%>
<%@page import="Models.Message"%>
<%@page import="javafx.util.Pair"%>

    
    <% 
     	DBObject obj = (DBObject)request.getServletContext().getAttribute(DBObject.ATTR_DB);
   	 	User user = (User)request.getSession().getAttribute(Constants.ATTR_USER);
   	 	ArrayList<Pair<Integer,Integer>> messages = obj.getMessages(user.getId());
    	 	if(messages != null){
    	 		ArrayList<Integer> unseen = obj.getUnseenMessages(user.getId());
	 			for(Pair<Integer,Integer> m : messages){
	 				String senderName = obj.getUserNameById(m.getValue());
	 				if(unseen != null && unseen.contains(m.getKey())) {
	 					out.print("<br>From: <a href ="+Constants.getMessageURL(m.getKey())+"> <b>"+senderName+"</b> </a>");
	 				} else {
						out.print("<br>From: <a href ="+Constants.getMessageURL(m.getKey())+"> "+senderName+"</a>");
	 				}
	 			}
 		} 
    %>