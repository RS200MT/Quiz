<%@page import="Models.Constants"%>
<style>
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
	background-color: #3e8e41;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown a:hover {
	background-color: #f1f1f1
}

.show {
	display: block;
}
</style>


<%
	if (request.getAttribute(Constants.ATTR_QUIZ_ID_FOR_QUESTION) == null)
		return;
	int quizId = (int) request.getAttribute(Constants.ATTR_QUIZ_ID_FOR_QUESTION);
%>
<script>
		function displayForm(object, id) {
			for (var i = 1; i < <%=Constants.QUESTION_TYPES_LENGTH+1 %>; i++) { 
				if (i == id) {
					document.getElementById("type" + i).style.display = "block";
					document.getElementById('<%=Constants.ADD_QUESTION_TYPE %>').value = id;
				} else
					document.getElementById("type" + i).style.display = "none";
			}
			document.getElementById("question_type").innerHTML = object.innerHTML;
		}
		
		var correctAnswersNum = 1;
		function addCorrectAnswer(){
			correctAnswersNum++;
			document.getElementById("removeFieldButton").style.display = "inline";
			var newField = document.createElement("p");
			newField.setAttribute("id", "<%=Constants.ADD_QUESTION_ANSWER%>" + correctAnswersNum);
			newField.innerHTML = 'Correct answer ' + correctAnswersNum + ': <input type="text" required="required" name="<%=Constants.ADD_QUESTION_ANSWER%>' + correctAnswersNum + '"/>';
		    document.getElementById('<%=Constants.ADD_QUESTION_ANSWER%>').appendChild(newField);
		}
		
		function removeAnswersField() {
			document.getElementById('<%=Constants.ADD_QUESTION_ANSWER%>' + correctAnswersNum).remove();
			correctAnswersNum--;
			if(correctAnswersNum == 1)
				document.getElementById("removeFieldButton").style.display = "none";
		}
	</script>


<h2>Create new question</h2>
<p>Click on the button to choose question type.</p>

<div class="dropdown">
	<button onclick="myFunction()" class="dropbtn" id="question_type">Question-response</button>
	<div id="myDropdown" class="dropdown-content">
		<a href="#1" onClick="displayForm(this, '1')">Question-response</a> <a
			href="#2" onClick="displayForm(this, '2')">Fill in blank</a> <a
			href="#3" onClick="displayForm(this, '3')">Multiple choice
			question</a> <a href="#4" onClick="displayForm(this, '4')">Picture
			question</a>
	</div>
</div>


<form action="addQuestion" method="post">
	<p>
		Question: <input type="text" required="required"
			name="<%=Constants.ADD_QUESTION_QUESTION%>"
			id="<%=Constants.ADD_QUESTION_QUESTION%>" />
	</p>
	<div id="type1"></div>
	<div id="type2" style="display: none"></div>
	<div id="type3" style="display: none">
		<p>
			Possible Answer 1: <input type="text"
				name="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>1"
				id="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>1" />
		</p>
		<p>
			Possible Answer 2: <input type="text"
				name="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>2"
				id="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>2" />
		</p>
		<p>
			Possible Answer 3: <input type="text"
				name="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>3"
				id="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>3" />
		</p>
		<p>
			Possible Answer 4: <input type="text"
				name="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>4"
				id="<%=Constants.ADD_QUESTION_POSSIBLE_ANSWER%>4" />
		</p>
	</div>
	<div id="type4" style="display: none">
		<p>
			Image URL: <input type="text"
				name="<%=Constants.ADD_QUESTION_IMAGE%>"
				id="<%=Constants.ADD_QUESTION_IMAGE%>" />
		</p>
	</div>

	<p id="<%=Constants.ADD_QUESTION_ANSWER%>">
		Correct answer 1: <input type="text"
			name="<%=Constants.ADD_QUESTION_ANSWER%>1"
			id="<%=Constants.ADD_QUESTION_ANSWER%>1" />
	</p>
	<input type="hidden" required="required"
		name="<%=Constants.ADD_QUESTION_TYPE%>"
		id="<%=Constants.ADD_QUESTION_TYPE%>" readonly /> <input
		type="hidden" required="required"
		name="<%=Constants.ADD_QUESTION_QUIZ_ID%>"
		id="<%=Constants.ADD_QUESTION_QUIZ_ID%>" value="<%=quizId%>" readonly />
	<p>
		<input type="submit" value="Next Question"
			name="<%=Constants.ADD_QUESTION_NEXT_QUESTION%>"
			id="<%=Constants.ADD_QUESTION_NEXT_QUESTION%>" />
	</p>
	<p>
		<input type="submit" value="Done"
			name="<%=Constants.ADD_QUESTION_DONE_QUIZ%>"
			id="<%=Constants.ADD_QUESTION_DONE_QUIZ%>" />
	</p>
</form>
<button onClick="addCorrectAnswer()">Add another correct answer</button>
<button onClick="removeAnswersField()" id="removeFieldButton"
	style="display: none">remove</button>
<script>
		document.getElementById('<%=Constants.ADD_QUESTION_TYPE%>').value = 1;
	function myFunction() {
		document.getElementById("myDropdown").classList.toggle("show");
	}

	window.onclick = function(event) {
		if (!event.target.matches('.dropbtn')) {

			var dropdowns = document.getElementsByClassName("dropdown-content");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show')) {
					openDropdown.classList.remove('show');
				}
			}
		}
	}
</script>
