package Questions;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;

public class Question {
	public enum QuestionType{
		QuestionResponse, FillInBlank, MultipleChoice, PictureResponse
	}
	
	private QuestionType qType;
	private String question;
	private List<String> answer;
	private List<String> possibleAnswers;
	private String imageUrl;
	
	public Question(QuestionType t, List<Object> questionInfo) {
		this.qType = t;
		switch (qType) {
		case QuestionResponse:
			this.question = (String) questionInfo.get(0);
			this.answer = new ArrayList<String>((ArrayList<String>)questionInfo.get(1));
			break;
		case FillInBlank:
			this.question = (String) questionInfo.get(0);
			this.answer = new ArrayList<String>((ArrayList<String>)questionInfo.get(1));
			break;
		case MultipleChoice:
			this.question = (String) questionInfo.get(0);
			this.answer = new ArrayList<String>((ArrayList<String>)questionInfo.get(1));
			this.possibleAnswers = new ArrayList<String>((ArrayList<String>)questionInfo.get(2));
		case PictureResponse:
			this.question = (String) questionInfo.get(0);
			this.answer = new ArrayList<String>((ArrayList<String>)questionInfo.get(1));
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
	
	public List<String> getAnswer() {
		return this.answer;
	}
	
	public List<String> getPossibleAnswers() {
		return this.possibleAnswers;
	}
	
	public String getImageURL() {
		return this.imageUrl;
	}
 }
