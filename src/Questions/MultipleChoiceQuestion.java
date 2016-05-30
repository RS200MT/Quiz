package Questions;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

public class MultipleChoiceQuestion {
	private String question = "";
	private String correctAnswer = "";
	private List<String> choices;
		
	public MultipleChoiceQuestion(String question, String answer, List<String> questions){
		this.question = question;
		choices = new ArrayList<String>(questions);
		correctAnswer = answer;
	}
	
	public String getAnswer(){
		return correctAnswer;
	}
	
}
