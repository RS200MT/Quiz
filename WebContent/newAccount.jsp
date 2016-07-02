<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
	if (curUser == null) {
		String alreadyExists = (String) request.getAttribute(Constants.ATTR_USERNAME_EXISTS);
		if (alreadyExists != null)
			out.println(alreadyExists);
%>
<form action="<%=Constants.S_REGISTER%>" method="post">
	<p>
		UserName: <br> <small>*Use only letters and/or numbers</small>
			<input type="text" required="required"
			name="<%=Constants.REGISTER_USERNAME%>"
			id="<%=Constants.REGISTER_USERNAME%>" />
	<p>
		Email: <input type="email" required="required"
			name="<%=Constants.REGISTER_EMAIL%>"
			id="<%=Constants.REGISTER_EMAIL%>" />
	<p>
		Password: <input type="password" required="required"
			name="<%=Constants.REGISTER_PASSWORD%>"
			id="<%=Constants.REGISTER_PASSWORD%>" />
	</p>
	<p>
		<input type="submit" value="<%=Constants.B_REGISTER%>" />
	</p>
</form>
<%
	} else {
		String toInclude = Constants.INDEX_DO_HOMEPAGE + ".jsp";
%>
To create new user, please log out and try registering<HR>
<jsp:include page="<%=toInclude%>" />
<%
	}
%>