package Models;

import java.util.ArrayList;
import java.util.List;

import Questions.Question;

public class Quiz {
	private int id;
	private String author; // User author?
	private List<Question> questions;
	private int timesWritten;
	
	public Quiz(int id, String author) {
		this.id = id;
		this.author = author;
		this.questions = new ArrayList<Question>();
		this.timesWritten = 0;
	}
	
	public Quiz(int id, String author, List<Question> questions, int timesWritten) {
		this.id = id;
		this.author = author;
		this.questions = questions;
		this.timesWritten = timesWritten;
	}
	
	
	public void addQuestion(Question q) {
		this.questions.add(q);
	}
	
	public void IncreaseTimesWrittenBy(int num) {
		this.timesWritten += num;
	}
	
	public int getID() {
		return this.id;
	}
	
	public List<Question> getQuestions() {
		return this.questions;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public int getTimesWritten() {
		return this.timesWritten;
	}
}
