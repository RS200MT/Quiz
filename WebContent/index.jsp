<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="Questions.Question"%>
<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
boolean isOwnReferer = request.getHeader("referer") != null
&& request.getHeader("referer").indexOf(Constants.SITE_URL) > -1;


	HashMap<String, String> pages = (HashMap<String, String>) getServletContext()
			.getAttribute(Constants.INDEX_HASHMAP);
	boolean loggedIn = ((User) request.getSession().getAttribute(Constants.ATTR_USER)) != null;
	String toInclude = Constants.INDEX_DO_HOMEPAGE;
	if (!loggedIn) {
		toInclude = (request.getParameter(Constants.INDEX_DO) != null
				&& request.getParameter(Constants.INDEX_DO).equals(Constants.INDEX_DO_REGISTER))
						? Constants.INDEX_DO_REGISTER : Constants.INDEX_DO_PROFILE_INFO;
	} else  {
		boolean doOther = request.getParameter(Constants.INDEX_DO) != null
				&& pages.containsKey(request.getParameter(Constants.INDEX_DO));
		if (doOther)
			toInclude = request.getParameter(Constants.INDEX_DO);
	}
	String pageTitle = pages.get(toInclude);
	toInclude += ".jsp";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=pageTitle%></title>
</head>
<body>
	<style>
.leftSidebar {
	float: left;
	width: 24%;
	border: 1px solid;
}

.rightSidebar {
	float: left;
	width: 24%;
	border: 1px solid;
}

.content {
	float: left;
	width: 51%;
	border: 1px solid;
}
</style>
	<div class="leftSidebar">
		<jsp:include page="Left.jsp" />
	</div>
	<div class="content">
		<jsp:include page="<%=toInclude%>" />
	</div>
	<div class="rightSidebar">
		<jsp:include page="Right.jsp" />
	</div>
</body>
</html>