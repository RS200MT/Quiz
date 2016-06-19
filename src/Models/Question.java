package Models;

import java.util.ArrayList;

public abstract class Question {
	protected String question; 
	protected ArrayList<String> answers;
	
	public String getQuestion() {
		return this.question;
	}
	
	public int isCorrect(String answer) {
		for (int i = 0; i < this.answers.size(); i++)
			if (this.answers.get(i).equals(answer))
				return 1;
		return 0;
	}
	
	public String getHTML(int index) {
		return "";
	}
	
	public int getType() {
		return 0;
	}
}
