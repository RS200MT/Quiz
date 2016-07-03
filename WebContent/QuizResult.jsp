<%@page import="Models.Constants"%>
<%@page import="Models.User"%>
<%@page import="Models.Quiz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	
User curUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
int quizId = Integer.parseInt(request.getParameter("quizId"));
if (request.getAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_MESSAGE) != null) {
	out.print(request.getAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_MESSAGE));
}
%>

<script>


function show(){
	document.getElementById("challenge").style.display = 'block';
}

function ajaxSearch1(type) {
	var url = "";
	if (type == 1) {
		var val = document.getElementById("challenge").value;
		if (val == "") {
			document.getElementById("challenge").innerHTML = "";
			return;
		}
		url = 'ajaxChallenge.jsp?<%=Constants.AJAX_USER_SEARCH%>='+ val + '&id=' +<%=curUser.getId()%> + '&quizId=' + <%=quizId%>;
	} 
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) 
    		document.getElementById("ajaxResul").innerHTML = xhttp.responseText;
	};
	xhttp.open('GET', url, true);
xhttp.send();
}
</script>
<div class="block" id="">
	<input type="text" id="challenge" placeholder="enter username of your friend" 
				onkeyup="ajaxSearch1(1)" style="display:none">
				<button onClick="show();">Challenge Friend</button>
	<div id="ajaxResul"></div>
</div>