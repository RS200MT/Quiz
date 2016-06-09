package Questions;

import java.util.ArrayList;


import org.apache.catalina.tribes.util.Arrays;




public class Question {
	public static enum QuestionType {
		NULL, 
		QuestionResponse{
			@Override
			public int getType(){
				return 1;
			}
		}, 
		FillInBlank{
			@Override
			public int getType(){
				return 2;
			}
		},
		MultipleChoice{
			@Override
			public int getType(){
				return 3;
			}
		}, 
		PictureResponse{
			@Override
			public int getType(){
				return 4;
			}
		};
		
		public int getType(){
			return 0;
		}
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
			break;
		case PictureResponse:
			this.imageUrl = (String) questionInfo.get(2);
			break;
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
