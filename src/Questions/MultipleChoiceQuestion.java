package Questions;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

public class MultipleChoiceQuestion {
	private String correctAnswer = "";
	private List<String> choices;
	
	
	public MultipleChoiceQuestion(String answer, List<String> questions){
		choices = new ArrayList<String>(questions);
		correctAnswer = answer;
	}
	
	public String getAnswer(){
		return correctAnswer;
	}
}
