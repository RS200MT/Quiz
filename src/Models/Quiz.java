package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Quiz {
	private int id;
	private String title;
	private String description;
	private String author;
	private String createTime;
	private int timesWritten;
	private boolean randomized;
	private boolean immediateCorrection;
	private ArrayList<Question> questions;

	private boolean displaySingleQuestion;
	private int currentQuestionIndex;
	private ArrayList<String> userAnswers;
	private int score;
	private long startTime;

	public Quiz(int id, String title, String description, String author, String createTime, int timesWritten,
			boolean randomized, boolean immediateCorrection, ArrayList<Question> questions,
			boolean displaySingleQuestion) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.createTime = createTime;
		this.timesWritten = timesWritten;
		this.questions = questions != null ? questions : new ArrayList<Question>();
		this.randomized = randomized;
		this.immediateCorrection = immediateCorrection;
		this.displaySingleQuestion = displaySingleQuestion;
		if (this.randomized)
			randomizeQuestions();
		this.currentQuestionIndex = 0;
		this.score = 0;
		this.userAnswers = new ArrayList<String>();
		this.startTime = 0;
	}

	private void randomizeQuestions() {
		Collections.shuffle(this.questions);
	}

	public void setUserAnswer(String answer) {
		this.score += this.questions.get(this.userAnswers.size()).isCorrect(answer);
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

	public String getCreateTime() {
		return this.createTime;
	}

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
		result += "Quiz Title: <b>" + getTitle() + "</b><HR>";
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

	public int getCurrentQuestionIndex() {
		return this.currentQuestionIndex;
	}

	public boolean hasMoreQuestions() {
		return this.questions.size() > this.currentQuestionIndex;
	}

	public boolean isLastQuestion() {
		return this.currentQuestionIndex == this.questions.size() - 1;
	}

	public void setStartTime(long time) {
		this.startTime = time;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public long getThisQuizTime(long endTime) {
		long difference = endTime - this.startTime;
		return difference;
	}

	public int getScore() {
		return this.score;
	}
}