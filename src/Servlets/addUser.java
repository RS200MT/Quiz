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
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Models.Constants.INDEX).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter(Constants.REGISTER_USERNAME);
		String email = request.getParameter(Constants.REGISTER_EMAIL);
		String password = Password.getHash(request.getParameter(Constants.REGISTER_PASSWORD));
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		if (obj == null) {
			obj = new DBObject();
			getServletContext().setAttribute(DBObject.ATTR_DB, obj);
		}
		if(obj.addUser(name, email, password)){
			User newUser = obj.getUserByUserName(name);
			request.getSession().setAttribute(Models.Constants.ATTR_USER, newUser);
			request.getRequestDispatcher(Models.Constants.INDEX).forward(request, response);
		} else {
			request.setAttribute(Constants.ATTR_USERNAME_EXISTS, Constants.REGISTER_USERNAME_EXISTS);
			request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_REGISTER)).forward(request, response);
		}
	}

}
