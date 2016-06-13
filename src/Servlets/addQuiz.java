package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Constants;
import Models.DBObject;
import Models.User;

/**
 * Servlet implementation class addQuiz
 */
@WebServlet("/addQuiz")
public class addQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addQuiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(Models.Constants.INDEX).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter(Constants.ADD_QUIZ_TITLE);
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		User user = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		int quizId = obj.addQuiz(title, user.getId());
		request.setAttribute(Constants.ATTR_QUIZ_ID_FOR_QUESTION, quizId);
		request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_ADD_QUESTION)).forward(request, response);
	}

}
