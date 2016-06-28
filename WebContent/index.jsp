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
<link rel="stylesheet" type="text/css" href='style/style.css'>
</head>
<body>
	<div class="header">
		<center>
			<a href="./"><img src="images/logo.png"></a>
		</center>
	</div>
	<table style="width: 100%">
		<tr>
			<td class="leftSidebar"><jsp:include page="Left.jsp" /></td>
			<td class="content">
				<div class="block-header" id="content"><%=pageTitle%></div>
				<div class="block" id="content">
					<jsp:include page="<%=toInclude%>" />
				</div>
			</td>
			<td class="rightSidebar"><jsp:include page="Right.jsp" /></td>
		</tr>
	</table>
	<div class="footer">
		<center><table>
			<tr>
				<th>©</th>
				<th>Tornike Jijiashvili |</th>
				<th>Mery Chonishvili |</th>
				<th>Waska Chaduneli</th>
			</tr>
			<tr>
				<td></td>
				<td><center><a href="https://fb.com/100006717485500" target="_blank"><img src="http://graph.facebook.com/100006717485500/picture?width=200&height=200" width="85px" height="85px" style="border: 1px solid rgba(255, 255, 255, 0.5)"></a></center></td>
				<td><center><a href="https://fb.com/100000706686861" target="_blank"><img src="http://graph.facebook.com/100000706686861/picture?width=200&height=200" width="85px" height="85px" style="border: 1px solid rgba(255, 255, 255, 0.5)"></a></center></td>
				<td><center><a href="https://fb.com/100001024236488" target="_blank"><img src="http://graph.facebook.com/100001024236488/picture?width=200&height=200" width="85px" height="85px" style="border: 1px solid rgba(255, 255, 255, 0.5)"></a></center></td>
			</tr>
		</table></center>
	</div>
</body>
</html>