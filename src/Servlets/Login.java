package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Constants;
import Models.DBObject;
import Models.Password;
import Models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		String passed_username = request.getParameter(Constants.LOGIN_USERNAME);
		String passed_password = request.getParameter(Constants.LOGIN_PASSWORD);
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		String db_password = obj.getPasswordHash(passed_username);
		if (db_password != null) {
			if (Password.passwordMatches(db_password, passed_password)) {
				User loggedUser = obj.getUserByUserName(passed_username);
				request.getSession().setAttribute(Constants.ATTR_USER, loggedUser);
			} else {
				request.setAttribute(Constants.ATTR_FAILED_LOGIN, Constants.LOGIN_INCORRECT_PASSWORD);
			}
		} else {
			request.setAttribute(Constants.ATTR_FAILED_LOGIN, Constants.LOGIN_USER_NOT_FOUND);
		}
		request.getRequestDispatcher(Constants.INDEX).forward(request, response);
	}

}
