package Questions;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion {
	private String question = "";
	private String correctAnswer = "";
	private List<String> choices;
		
	public MultipleChoiceQuestion(String question, String answer, List<String> possibleAnswers){
		this.question = question;
		choices = new ArrayList<String>();
		correctAnswer = answer;
	}
	
	public String getAnswer(){
		return correctAnswer;
	}
	
}
