package Servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.DBObject;
import Models.Password;

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

	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	private String getHash(String word) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(word.getBytes());
			return hexToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
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
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} else {
				System.out.println("NOT LOGGED");
				request.getRequestDispatcher("incorrect.jsp").forward(request, response);
			}
		} else {
			System.out.println("User not found!");
		}
	}

}
