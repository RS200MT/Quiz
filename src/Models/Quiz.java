package Models;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Date;
=======
import java.util.Collections;
>>>>>>> 4bcc634f0c89cf1a9078f2a038015347c9ac0d26
import java.util.HashMap;
import java.util.List;

public class Quiz {
	private int id;
	private String title;
	private String description;
	private String author;
	private String createTime;
	private int timesWritten;
<<<<<<< HEAD
	private int currentQuestion;
	private boolean singleQuestion;
	private String title;
	private Date startTime;
	private double spentTime;
=======
	private boolean randomized;
	private boolean immediateCorrection;
	private ArrayList<Question> questions;

	private boolean displaySingleQuestion;
	private int currentQuestionIndex;
	private ArrayList<String> userAnswers;
>>>>>>> 4bcc634f0c89cf1a9078f2a038015347c9ac0d26

	public Quiz(int id, String title, String description, String author, String createTime, int timesWritten,
			boolean randomized, boolean immediateCorrection, ArrayList<Question> questions,
			boolean displaySingleQuestion) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.createTime = createTime;
		this.timesWritten = timesWritten;
		this.questions = new ArrayList<Question>();
<<<<<<< HEAD
		this.userAnswers = new HashMap<Integer, String>();
		this.currentQuestion = 0;
		this.singleQuestion = true;
		this.title = title;
		this.spentTime = 0;
=======
		this.randomized = randomized;
		this.immediateCorrection = immediateCorrection;
		this.displaySingleQuestion = displaySingleQuestion;
		if (this.randomized)
			randomizeQuestions();
		currentQuestionIndex = 0;
		userAnswers = new ArrayList<String>();
>>>>>>> 4bcc634f0c89cf1a9078f2a038015347c9ac0d26
	}

	private void randomizeQuestions() {
		Collections.shuffle(this.questions);
	}

	public void setUserAnswer(String answer) {
		userAnswers.add(answer);
	}

	public int getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public String getAuthor() {
		return this.author;
	}

<<<<<<< HEAD
	public void setStartTime(Date starTime){
		this.startTime = starTime;
	}
	
	public Date getStartTime(){
		return this.startTime;
	}
	
	public void setSpentTime(double spentTime){
		this.spentTime = spentTime;
	}
	
	public double getSpentTime(){
		return this.spentTime;
	}
	
=======
	public String getCreateTime() {
		return this.createTime;
	}

>>>>>>> 4bcc634f0c89cf1a9078f2a038015347c9ac0d26
	public int getTimesWritten() {
		return this.timesWritten;
	}

	public boolean isRandomized() {
		return this.randomized;
	}

	public boolean isImmediateCorrection() {
		return this.immediateCorrection;
	}

	public void addQuestion(Question q) {
		this.questions.add(q);
	}

	public ArrayList<Question> getQuestions() {
		return this.questions;
	}

	public boolean displaySingleQuestion() {
		return this.displaySingleQuestion;
	}

	public String getHTML() {
		String result = "";
		// string title
		if (!this.displaySingleQuestion) {
			result += getAllQuestionsHTML();
		} else {
			if (currentQuestionIndex < this.questions.size()) {
				result += this.questions.get(currentQuestionIndex).getHTML(currentQuestionIndex) + "<BR>";
			}
		}
		return result;
	}

	private String getAllQuestionsHTML() {
		String result = "";
		for (int i = 0; i < this.questions.size(); i++) {
			result += this.questions.get(i).getHTML(i) + "<HR>";
		}
		return result;
	}

	public void increaseQuestionCounter() {
		this.currentQuestionIndex++;
	}

	public boolean hasMoreQuestions() {
		return this.questions.size() > this.currentQuestionIndex;
	}

	public int getScore() {
		int score = 0;
		for (int i = 0; i < this.userAnswers.size(); i++) {
			score += this.questions.get(i).isCorrect(this.userAnswers.get(i));
		}
		return score;
	}

	/*
	 * public int getCurrentIndex() { return currentQuestion; }
	 * 
	 * public int increaseQuestionIndex() { return ++currentQuestion; }
	 * 
	 * public void setAnswer(int questionIndex, String answer) {
	 * this.userAnswers.put(questionIndex, answer); }
	 * 
	 * public HashMap<Integer, String> getUserAnswers() { return
	 * this.userAnswers; }
	 * 
	 * public boolean hasMoreQuestions() { return this.currentQuestion <
	 * this.questions.size(); }
	 * 
	 * public Question getQuestion() { if (!hasMoreQuestions()) restart();
	 * return this.questions.get(this.currentQuestion++); }
	 * 
	 * public int getScore() { int result = 0; for (int i = 0; i <
	 * getQuestions().size(); i++) { if (getUserAnswers().containsKey(i)) {
	 * String userAnswer = getUserAnswers().get(i); if
	 * (getQuestions().get(i).checkAnswer(userAnswer)) result++; } } return
	 * result; }
	 * 
	 * public boolean isSingleQuestion() { return this.singleQuestion; }
	 * 
	 * public void allQuestionsOnPage() { this.singleQuestion = false; }
	 * 
	 * public String toHTMLall() { String res = ""; for (int i = 0; i <
	 * getQuestions().size(); i++) res += getQuestions().get(i).toHTML(i) +
	 * "<HR>"; return res; }
	 * 
	 * public String toHTMLsingle() { String res =
	 * getQuestion().toHTML(getCurrentIndex() - 1); return res; }
	 * 
	 * public void restart() { this.currentQuestion = 0; }
	 */
}
