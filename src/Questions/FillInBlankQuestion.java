package Questions;

public class FillInBlankQuestion {
	
	private final int SCORE_FOR_THIS_QUESTION = 1;
	
	private String question = "";
	private String correctAnswer = "";
	
	public FillInBlankQuestion(String question, String correctAnswer) {
		this.question = question;
		this.correctAnswer = correctAnswer;
	}
	
	public String getAnswer() {
		return this.correctAnswer;
	}
}
