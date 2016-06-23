<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	boolean loggedIn = ((User) request.getSession().getAttribute(Constants.ATTR_USER)) != null;
	if (loggedIn) {
%>
<div class="block-header" id="profileInfo">Profile</div>
<div class="block" id="profileInfo">
	<%
		String profileInfo = Constants.INDEX_DO_PROFILE_INFO + ".jsp";
	%>
	<jsp:include page="<%=profileInfo%>" />
</div>
<%
	}
%>
<div class="block-header" id="">block2</div>
<div class="block" id="">block content 2 left</div>

<div class="block-header" id="">block3</div>
<div class="block" id="">block content 3 left</div>

<div class="block-header" id="">block4</div>
<div class="block" id="">block content 4 left</div>