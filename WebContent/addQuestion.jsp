<!DOCTYPE html>
<%@page import="Questions.Question.QuestionType"%>
<%@page import="Models.Constants"%>
<html>
<head>
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


</head>
<body>
	<script>
		function displayForm(id) {
			for (var i = 1; i < 5; i++) {
				if (i == id) {
					document.getElementById("type" + i).style.display = "block";
					document.getElementById("ADDQUESTION_TYPE").value = id;
				} else
					document.getElementById("type" + i).style.display = "none";
			}
		}
	</script>
	
	
	<h2>Create new question</h2>
	<p>Click on the button to choose question type.</p>

	<div class="dropdown">
		<button onclick="myFunction()" class="dropbtn">Question Type</button>
		<div id="myDropdown" class="dropdown-content">
			<a href="#1" onClick="displayForm('1')">Question-response</a> <a
				href="#2" onClick="displayForm('2')">Fill in blank</a> <a href="#3"
				onClick="displayForm('3')">Multiple choice question</a> <a href="#4"
				onClick="displayForm('4')">Picture question</a>
		</div>
	</div>


	<form action="addQuestion" method="post">
		<div id="type1" style="display: none">
			<p>Question:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_QUESTION);%>></p>
			<p>Correct answer:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER);%>></p>
			<p><input type="submit" value="Done"> </p>
			<p><input type="submit" value="Next Question"> </p>
		</div>
		
		<div id="type2" style="display: none">
			<p>Question:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_QUESTION);%>></p>
			<p>Correct answer:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER);%>></p>
			<p><input type="submit" value="Done"> </p>
			<p><input type="submit" value="Next Question"> </p>
		</div>
		
		<div id="type3" style="display: none">
			<p>Question:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_QUESTION);%>></p>
			<p>Possible Answer 1:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER+"1");%>> </p>
			<p>Possible Answer 2:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER+"2");%>> </p>
			<p>Possible Answer 3:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER+"3");%>> </p>
			<p>Possible Answer 4:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER+"4");%>> </p>
			<p>Correct answer:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER);%>></p>
			<p><input type="submit" value="Done"> </p>
			<p><input type="submit" value="Next Question"> </p>
		</div>
		
		<div id="type4" style="display: none">
			<p>Question:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_QUESTION);%>></p>
			<p>Correct answer:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_ANSWER);%>></p>
			<p>Image URL:<input type="text" required="required" name=<%out.print(Constants.ADD_QUESTION_IMAGE); %>></p>
			<p><input type="submit" value="Done"> </p>
			<p><input type="submit" value="Next Question"> </p>
		</div>
		<input type="hidden" required="required" name="ADDQUESTION_TYPE"
			id="ADDQUESTION_TYPE" readonly />
	</form>
	<script>
		/* When the user clicks on the button, 
		 toggle between hiding and showing the dropdown content */
		function myFunction() {
			document.getElementById("myDropdown").classList.toggle("show");
		}

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {

				var dropdowns = document
						.getElementsByClassName("dropdown-content");
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