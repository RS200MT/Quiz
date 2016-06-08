package Questions;

import java.util.ArrayList;

public class Question {
	public static enum QuestionType {
		NULL, QuestionResponse, FillInBlank, MultipleChoice, PictureResponse
	}

	private QuestionType qType;
	private String question;
	private ArrayList<String> answers;
	private ArrayList<String> possibleAnswers;
	private String imageUrl;

	public Question(QuestionType t, ArrayList<Object> questionInfo) {
		this.qType = t;
		this.question = (String) questionInfo.get(0);
		this.answers = new ArrayList<String>((ArrayList<String>) questionInfo.get(1));
		switch (qType) {
		case MultipleChoice:
			this.possibleAnswers = new ArrayList<String>((ArrayList<String>) questionInfo.get(2));
		case PictureResponse:
			this.imageUrl = (String) questionInfo.get(2);
		default:
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public String getQuestion() {
		return this.question;
	}

	public boolean checkAnswer(String answer) {
		for (int i = 0; i < answers.size(); i++)
			if (answers.get(i).equals(answer))
				return true;
		return false;
	}

	public ArrayList<String> getAnswer() {
		return this.answers;
	}

	public ArrayList<String> getPossibleAnswers() {
		return this.possibleAnswers;
	}

	public String getImageURL() {
		if (this.qType == QuestionType.PictureResponse)
			return this.imageUrl;
		return null;
	}

	public QuestionType getType() {
		return this.qType;
	}

}
