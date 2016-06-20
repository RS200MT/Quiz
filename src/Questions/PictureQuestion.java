package Questions;

import java.util.ArrayList;

import Models.Constants;
import Models.Question;

public class PictureQuestion extends Question {
	private String imageURL;
	
	public PictureQuestion(String question, ArrayList<String> answers, String imageURL) {
		this.question = question;
		this.answers = answers;
		this.imageURL = imageURL;
	}
	
	private String getImageHTML() {
		String result = "";
		result +="<img src='" + this.imageURL + "' width='350' height='200'/>";
		return result;
	}
	
	@Override
	public String getHTML(int index) {
		String result = "Question: <b>" + getQuestion() + "</b><BR>";
		result += getImageHTML() + "<BR>";
		result += "Answer: <input type='text' required='required' name='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index
					+ "' id='" + Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + index + "' />";
		return result;
	}
	
	public static int getType() {
		return 4;
	}
	
	@Override
	public ArrayList<String> getAdditionalData() {
		ArrayList<String> additionalData= new ArrayList<>();
		additionalData.add(this.imageURL);
		return additionalData;
	}
}
