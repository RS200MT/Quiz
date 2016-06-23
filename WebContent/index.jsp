<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	HashMap<String, String> pages = (HashMap<String, String>) getServletContext()
			.getAttribute(Constants.INDEX_HASHMAP);
	boolean loggedIn = ((User) request.getSession().getAttribute(Constants.ATTR_USER)) != null;
	String toInclude = Constants.INDEX_DO_HOMEPAGE;
	if (!loggedIn) {
		toInclude = (request.getParameter(Constants.INDEX_DO) != null
				&& request.getParameter(Constants.INDEX_DO).equals(Constants.INDEX_DO_REGISTER))
						? Constants.INDEX_DO_REGISTER : Constants.INDEX_DO_PROFILE_INFO;
	} else {
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
body {
	margin: 0px;
	background-color: #f1f1f1;
	color: #1f1f1f;
}

td.leftSidebar {
	width: 300px;
}

td.rightSidebar {
	width: 300px;
}

td.content {
	
}

div.block {
	padding: 5px;
	background-color: #ccc;
	margin-top: 0px !important;
	margin: 5px;
	border-radius: 0px 0px 7px 7px;
}

div.block#content {
	background-color: rgba(0, 0, 0, 0.5);
	color: #fff;
	margin: 0px;
	border-radius: 0px 0px 7px 7px;
}

div.block-header {
	padding: 5px;
	background-color: #000;
	color: #fff;
	margin-bottom: 0px !important;
	margin: 5px;
	border-radius: 7px 7px 0px 0px;
}

div.block-header#content {
	background-color: #ccc;
	margin-top: 5px !important;
	margin: 0px;
	color: #000;
}

div.footer {
	background-color: rgba(0, 0, 0, 0.7);
	border-radius: 7px 7px 0px 0px;
	color: #fff;
	margin: 5px;
	float: none;
	padding: 8px;
}

div.header {
	background-color: rgba(0, 0, 0, 0.7);
	border-radius: 0px 0px 7px 7px;
	color: #fff;
	margin-top: 0px !important;
	margin: 5px;
	padding: 8px;
}

table {
	margin: 0px;
	padding: 0px;
}

td {
	vertical-align: top;
}
</style>
	<div class="header">
		header content<BR> header content<BR>
	</div>
	<table style="width: 100%">
		<tr>
			<td class="leftSidebar"><jsp:include page="Left.jsp" /></td>
			<td class="content">
				<div class="block-header" id="content">content title</div>
				<div class="block" id="content">
					<jsp:include page="<%=toInclude%>" />
				</div>
			</td>
			<td class="rightSidebar"><jsp:include page="Right.jsp" /></td>
		</tr>
	</table>
	<div class="footer">
		footer content<BR> footer content<BR> footer content<BR>
		footer content<BR> footer content<BR> footer content<BR>
	</div>
</body>
</html>