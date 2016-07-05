<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%
	User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
	if (curUser == null) {
		String incorrectLogin = (String) request.getAttribute(Constants.ATTR_FAILED_LOGIN);
		if (incorrectLogin != null)
			out.println(incorrectLogin);
%>
<form action="<%=Constants.S_LOGIN%>" method="post">
	<p>
		Username: <input type="text" required="required"
			name="<%=Constants.LOGIN_USERNAME%>"
			id="<%=Constants.LOGIN_USERNAME%>" />
	<p>
		Password: <input type="password" required="required"
			name="<%=Constants.LOGIN_PASSWORD%>"
			id="<%=Constants.LOGIN_PASSWORD%>" />
	<p>
		<input type="submit" value="<%=Constants.B_LOGIN%>">
</form>
<input onClick="window.location.href = '<%=Constants.getAction(Constants.INDEX_DO_REGISTER)%>'" type="submit" value="Register new account" style="width:200px; height:30px; padding:0px; line-height:30px">

<%
	} else {
		out.println(curUser.getUserName());
%>
<form action="<%=Constants.S_LOGOUT%>" method="post">
	<input type="submit" value="<%out.print(Constants.B_LOGOUT);%>" id="logout"/>
</form>

<a href="<%=Constants.getAction(Constants.INDEX_DO_ADD_QUIZ)%>"><%=Constants.B_ADD_QUIZ%></a>
<a href="<%=Constants.getAction(Constants.INDEX_DO_MY_QUIZES)%>"><%=Constants.B_MY_QUIZES%></a>
<%
	}
%>