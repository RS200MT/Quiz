package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Quiz curQuiz = null;
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		if (request.getParameter(Constants.QUIZINT_SINGLE_QUESTION) != null) {
			int singleQuestion = Integer.parseInt(request.getParameter(Constants.QUIZINT_SINGLE_QUESTION));
			int quizId = Integer.parseInt(request.getParameter(Constants.ATTR_QUIZ_ID_FOR_QUESTION));
			curQuiz = null;
			try {
				curQuiz = obj.getQuizById(quizId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			curQuiz.setStartTime(new Date());
			if (singleQuestion == 2)
				curQuiz.allQuestionsOnPage();
		} else {
			curQuiz = (Quiz) request.getSession().getAttribute(Constants.ATTR_SESSION_QUIZ);
			if (!curQuiz.isSingleQuestion()) {
				getAnswersAndCheck(curQuiz, request, response);
			} else {
				curQuiz.setAnswer(curQuiz.getCurrentIndex() - 1, request
						.getParameter(Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + (curQuiz.getCurrentIndex() - 1)));
				if (!curQuiz.hasMoreQuestions()) {
					request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_SCORE, curQuiz.getScore());
					request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_FINISHED, 1);
					curQuiz.restart();
					fixEndTime(curQuiz);
					obj.addToQuizLog(((User)request.getSession().getAttribute(Constants.ATTR_USER)).getId(),curQuiz);
				}
			}
		}
		redirectToQuizPage(curQuiz, request, response);
	}

	private void fixEndTime(Quiz curQuiz) {
		Date date = new Date();
		curQuiz.setSpentTime(date.getSeconds() - curQuiz.getStartTime().getSeconds());
	}

	private void getAnswersAndCheck(Quiz curQuiz, HttpServletRequest request, HttpServletResponse response) {
		for (int i = 0; i < curQuiz.getQuestions().size(); i++) {
			String answer = request.getParameter(Constants.INDEX_DO_QUIZ_QUESTION_ANSWER + i);
			curQuiz.setAnswer(i, answer);
		}
		int score = curQuiz.getScore();
		request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_FINISHED, 1);
		request.setAttribute(Constants.INDEX_DO_QUIZ_ATTR_RESULT_SCORE, score);
		curQuiz.restart();
	}

	private void redirectToQuizPage(Quiz curQuiz, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute(Constants.ATTR_SESSION_QUIZ, curQuiz);
			request.getRequestDispatcher(Constants.getQuizURL(curQuiz.getID())).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
