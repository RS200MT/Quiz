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
	private boolean singleQuestion;

	public Quiz(int id, String author, int timesWritten) {
		this.id = id;
		this.author = author;
		this.timesWritten = timesWritten;
		this.questions = new ArrayList<Question>();
		this.userAnswers = new HashMap<Integer, String>();
		this.currentQuestion = 0;
		this.singleQuestion = true;
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

	public int getCurrentIndex() {
		return currentQuestion;
	}

	public void setAnswer(int questionIndex, String answer) {
		this.userAnswers.put(questionIndex, answer);
	}
	
	public HashMap<Integer, String> getUserAnswers() {
		return this.userAnswers;
	}

	public boolean hasMoreQuestions() {
		return this.currentQuestion < this.questions.size();
	}

	public Question getQuestion() {
		return this.questions.get(this.currentQuestion++);
	}

	public void randomizeQuestions() {
		// TODO
	}
	
	public int getScore() {
		int result = 0;
		for (int i = 0; i < getQuestions().size(); i++) {
			if (getUserAnswers().containsKey(i)) {
				String userAnswer = getUserAnswers().get(i);
				if (getQuestions().get(i).checkAnswer(userAnswer))
					result++;
			}
		}
		return result;
	}

	public boolean isSingleQuestion() {
		return this.singleQuestion;
	}

	public void allQuestionsOnPage() {
		this.singleQuestion = false;
	}

	public String toHTMLall() {
		String res = "";
		for (int i = 0; i < getQuestions().size(); i++)
			res += getQuestions().get(i).toHTML(i) + "<HR>";
		return res;
	}

	public String toHTMLsingle() {
		String res = getQuestion().toHTML(getCurrentIndex() - 1);
		return res;
	}

}
