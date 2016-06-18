package Questions;

import java.util.ArrayList;

import Models.Constants;
import Models.Question;

public class QuestionResponse extends Question {
	public QuestionResponse(String question, ArrayList<String> answers) {
		this.question = question;
		this.answers = answers;
	}
	
	@Override
	public String getHTML(int index) {
		String result = "Question: <b>" + getQuestion() + "</b><BR>";
		result += "Answer: <input type='text' required='required' name='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index
					+ "' id='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index + "' />";
		return result;
	}
}
