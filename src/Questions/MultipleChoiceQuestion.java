package Questions;

import java.util.ArrayList;

import Models.Constants;
import Models.Question;

public class MultipleChoiceQuestion extends Question {
	private ArrayList<String> possibleAnswers;

	public MultipleChoiceQuestion(String question, ArrayList<String> answers, ArrayList<String> possibleAnswers) {
		this.question = question;
		this.answers = answers;
		this.answers = possibleAnswers;
	}

	private String getMultipleChoiceHTML(int index) {
		String result = "Select correct answer: <BR>";
		for (int i = 0; i < this.possibleAnswers.size(); i++) {
			result += "<input type='radio' name='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index
					+ "' id='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index + "' value='"
					+ this.possibleAnswers.get(i) + "' /> " + this.possibleAnswers.get(i) + "<BR>";
		}
		return result;
	}

	@Override
	public String getHTML(int index) {
		String result = "Question: <b>" + getQuestion() + "</b><BR>";
		result += getMultipleChoiceHTML(index);
		return result;
	}
	
	@Override
	public int getType() {
		return 3;
	}
}
