package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Constants;
import Models.DBObject;
import Models.Quiz;

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
		if (request.getParameter(Constants.QUIZINT_SINGLE_QUESTION) != null) {
			int singleQuestion = Integer.parseInt(request.getParameter(Constants.QUIZINT_SINGLE_QUESTION));
			int quizId = Integer.parseInt(request.getParameter(Constants.ATTR_QUIZ_ID_FOR_QUESTION));
			DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
			Quiz curQuiz = null;
			try {
				curQuiz = obj.getQuizById(quizId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (singleQuestion == 2)
				curQuiz.allQuestionsOnPage();
			request.getSession().setAttribute(Constants.ATTR_SESSION_QUIZ, curQuiz);
			request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_QUIZ_PAGE)).forward(request, response);
		} else {
			Quiz curQuiz = (Quiz) request.getSession().getAttribute(Constants.ATTR_SESSION_QUIZ);
			if (!curQuiz.isSingleQuestion()) {
				// ertianad unda shevamowmot chavyarot curQuiz is pasuxebshi pasuxebi
			} else {
				
			}
			request.getSession().setAttribute(Constants.ATTR_SESSION_QUIZ, curQuiz);
			request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_QUIZ_PAGE)).forward(request, response);
		}
		request.getRequestDispatcher(Constants.getAction(Constants.INDEX)).forward(request, response);
	}

}
