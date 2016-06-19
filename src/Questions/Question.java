package Questions;

import java.util.ArrayList;

import org.apache.catalina.tribes.util.Arrays;

import Models.Constants;

public class Question {
//	public static enum QuestionType {
//		NULL, QuestionResponse, FillInBlank, MultipleChoice, PictureResponse
//	}
//
//	private QuestionType qType;
	private String question;
	private ArrayList<String> answers;
	private ArrayList<String> possibleAnswers;
	private String imageUrl;

//	public Question(QuestionType t, ArrayList<Object> questionInfo) {
//		this.qType = t;
//		this.question = (String) questionInfo.get(0);
//		this.answers = new ArrayList<String>((ArrayList<String>) questionInfo.get(1));
//		switch (qType) {
//		case MultipleChoice:
//			this.possibleAnswers = new ArrayList<String>((ArrayList<String>) questionInfo.get(2));
//			break;
//		case PictureResponse:
//			this.imageUrl = (String) questionInfo.get(2);
//			break;
//		default:
//			break;
//		}
//	}

	public String getQuestion() {
		return this.question;
	}

	public boolean checkAnswer(String answer) {
		for (int i = 0; i < answers.size(); i++)
			if (answers.get(i).equals(answer))
				return true;
		return false;
	}

	public ArrayList<String> getAnswers() {
		return this.answers;
	}

	public ArrayList<String> getPossibleAnswers() {
		return this.possibleAnswers;
	}

	public String getImageURL() {
//		if (this.qType == QuestionType.PictureResponse)
//			return this.imageUrl;
		return null;
	}

//	public QuestionType getType() {
//		return this.qType;
//	}

//	public String toHTML(int index) {
//		String result = "Question: <b>" + getQuestion() + "</b><BR>";
//		if (getType() == QuestionType.PictureResponse)
//			result += "<img src='" + getImageURL() + "' width='350' height='200'/><BR>";
//		if (getType() == QuestionType.MultipleChoice)
//			result += getMultipleChoiceHTML(index);
//		else
//			result += "Answer: <input type='text' required='required' name='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index
//					+ "' id='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index + "' />";
//		return result;
//	}

	private String getMultipleChoiceHTML(int index) {
		String result = "Select correct answer: <BR>";
		for (int i = 0; i < getPossibleAnswers().size(); i++) {
			result += "<input type='radio' required='required' name='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index
					+ "' id='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index + "' value='" + getPossibleAnswers().get(i) +"' /> " + getPossibleAnswers().get(i) + "<BR>" ;
		}
		return result;
	}

}
