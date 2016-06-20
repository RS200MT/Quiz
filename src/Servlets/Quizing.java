package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.Timestamp;

import Models.Constants;
import Models.DBObject;
import Models.Quiz;
import Models.User;

/**
 * Servlet implementation class Quizing
 */
@WebServlet("/Quizing")
public class Quizing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Quizing() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(Constants.getAction(Constants.INDEX)).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Quiz curQuiz = getCurrentQuiz(request, response);
		if (request.getParameter(Constants.QUIZINIG_DONE) != null) {
			doneQuiz(request, response, curQuiz);
		} else if (request.getParameter(Constants.QUIZINIG_NEXT) != null) {
			nextQuestion(request, response, curQuiz);
		} else if (request.getParameter(Constants.QUIZINIG_CHECK) != null) {
			checkAnswer(request, response, curQuiz);
		} else if (request.getParameter(Constants.QUIZINIG_CHECK_RESULT_NEXT_QUESTION) != null) {
			nextQuestionAfterCheck(response, request, curQuiz);
		}
	}

	private void nextQuestionAfterCheck(HttpServletResponse response, HttpServletRequest request, Quiz curQuiz)
			throws ServletException, IOException {
		if (curQuiz.hasMoreQuestions())
			redirectAndSetQuizInSession(curQuiz, request, response);
		else
			redirectToResultPageAndDoneQuiz(request, response, curQuiz);
	}

	private void checkAnswer(HttpServletRequest request, HttpServletResponse response, Quiz curQuiz)
			throws ServletException, IOException {
		String answer = request
				.getParameter(Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + curQuiz.getCurrentQuestionIndex());
		if (answer == null)
			answer = "";
		int lastScore = curQuiz.getScore();
		curQuiz.setUserAnswer(answer);
		int newScore = curQuiz.getScore();
		curQuiz.increaseQuestionCounter();
		String message = "Your answer is: ";
		message += newScore == lastScore ? "INCORRECT!" : "CORRECT!";
		message += " | Your score: " + newScore;
		message += "<BR><form action='" + Constants.S_QUIZING + "' method='post'><input type='submit' name='"
				+ Constants.QUIZINIG_CHECK_RESULT_NEXT_QUESTION + "' value='Next Question'/></form>";
		request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_MESSAGE, message);
		request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_QUIZ_RESULT)).forward(request, response);
	}

	private void nextQuestion(HttpServletRequest request, HttpServletResponse response, Quiz curQuiz)
			throws ServletException, IOException {
		String answer = request
				.getParameter(Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + curQuiz.getCurrentQuestionIndex());
		if (answer == null)
			answer = "";
		curQuiz.setUserAnswer(answer);
		curQuiz.increaseQuestionCounter();
		if (curQuiz.hasMoreQuestions())
			redirectAndSetQuizInSession(curQuiz, request, response);
		else
			redirectToResultPageAndDoneQuiz(request, response, curQuiz);
	}

	private void doneQuiz(HttpServletRequest request, HttpServletResponse response, Quiz curQuiz)
			throws ServletException, IOException {
		for (int i = 0; i < curQuiz.getQuestions().size(); i++) {
			String answer = request.getParameter(Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + i);
			curQuiz.setUserAnswer(answer);
		}
		redirectToResultPageAndDoneQuiz(request, response, curQuiz);
	}

	private void redirectToResultPageAndDoneQuiz(HttpServletRequest request, HttpServletResponse response, Quiz curQuiz)
			throws ServletException, IOException {
		int score = curQuiz.getScore();
		String quizTime = logQuiz(curQuiz, score, request);
		request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_FINISHED, 1);
		request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_MESSAGE,
				"You're done. your score is: " + score + " | time: " + quizTime);
		request.getSession().setAttribute(Constants.ATTR_SESSION_QUIZ, null);
		request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_QUIZ_RESULT)).forward(request, response);
	}

	private String logQuiz(Quiz curQuiz, int score, HttpServletRequest request) {
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		java.util.Date date = new java.util.Date();
		long endTime = date.getTime();
		long quizTime = curQuiz.getThisQuizTime(endTime);
		User user = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		if (user != null)
			obj.logQuiz(user.getId(), curQuiz.getID(), score, curQuiz.getStartTime(), quizTime);
		return (int) quizTime / 1000 + " seconds";
	}

	private Quiz getCurrentQuiz(HttpServletRequest request, HttpServletResponse response) {
		Quiz curQuiz = null;
		if (request.getParameter(Constants.QUIZINT_SINGLE_QUESTION) != null) {
			int singleQuestion = Integer.parseInt(request.getParameter(Constants.QUIZINT_SINGLE_QUESTION));
			int quizId = Integer.parseInt(request.getParameter(Constants.ATTR_QUIZ_ID_FOR_QUESTION));
			DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
			try {
				curQuiz = obj.getQuizById(quizId, singleQuestion);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (curQuiz == null)
				System.out.println("Quiz not found!");
			java.util.Date date = new java.util.Date();
			curQuiz.setStartTime(date.getTime());
			redirectAndSetQuizInSession(curQuiz, request, response);
		} else
			curQuiz = (Quiz) request.getSession().getAttribute(Constants.ATTR_SESSION_QUIZ);
		return curQuiz;
	}

	private void redirectAndSetQuizInSession(Quiz curQuiz, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute(Constants.ATTR_SESSION_QUIZ, curQuiz);
			request.getRequestDispatcher(Constants.getQuizURL(curQuiz.getID())).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}