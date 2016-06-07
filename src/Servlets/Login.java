package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.DBObject;
import Models.Password;
import Models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATTR_FAILED_LOGIN = "failedLogin";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String passed_username = request.getParameter("username");
		String passed_password = request.getParameter("password");
		DBObject obj = (DBObject) getServletContext().getAttribute("DB");
		String db_password = obj.getPasswordHash(passed_username);
		if (db_password != null) {
			if (Password.passwordMatches(db_password, passed_password)) {
				System.out.println("LOGIN successfully");
				request.getSession().setAttribute(User.USER_ATTR, new User(passed_username, obj));
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				System.out.println("NOT LOGGED");
				request.setAttribute(ATTR_FAILED_LOGIN, "The password for username <b>" + passed_username + "</b> was incorrect!");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else {
			System.out.println("User not found!");
			request.setAttribute(ATTR_FAILED_LOGIN, "The username <b>" + passed_username + "</b> was not found!");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
