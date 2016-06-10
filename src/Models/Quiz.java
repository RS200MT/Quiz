package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Questions.Question;

public class Quiz {
	private int id;
	private String author;
	private List<Question> questions;
	private HashMap<Integer, String> userAnswers;
	private int timesWritten;
	private int currentQuestion;
	
	public Quiz(int id, String author, int timesWritten) {
		this.id = id;
		this.author = author;
		this.timesWritten = timesWritten;
		this.questions = new ArrayList<Question>();
		this.userAnswers = new HashMap<Integer, String>();
		this.currentQuestion = 0;
	}
	
	public void addQuestion(Question q) {
		this.questions.add(q);
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
	
	public int getCurrentIndex(){
		return currentQuestion;
	}
	
	public void setAnswer(int questionIndex, String answer) {
		userAnswers.put(questionIndex, answer);
	}
	
	public boolean hasMoreQuestions() {
		return currentQuestion < questions.size();
	}
	
	public Question getQuestion() {
		return questions.get(currentQuestion++);
	}
	
	public void randomizeQuestions() {
		//TODO
	}
	
	
	
}
