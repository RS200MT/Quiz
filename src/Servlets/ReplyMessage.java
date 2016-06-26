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
import Models.User;

/**
 * Servlet implementation class ReplyMessage
 */
@WebServlet("/ReplyMessage")
public class ReplyMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBObject db = (DBObject)getServletContext().getAttribute(DBObject.ATTR_DB);
		User sessionUser = (User) request.getSession().getAttribute(Constants.ATTR_USER);
		if(sessionUser == null) {
			request.getRequestDispatcher(Constants.INDEX).forward(request, response); // ??????????????????????
		} else {
			String reply = request.getParameter(Constants.MESSAGE_REPLY);
			if(reply!=null) {
				String replyText = request.getParameter(Constants.MESSAGE_REPLY_TEXT);
				int sendTo = Integer.parseInt(request.getParameter(Constants.MESSAGE_REPLY_TO));
				if(replyText!=null && !replyText.equals("")) {
					try {
						db.addSentMessage(sessionUser.getUserName(), db.getUserNameById(sendTo), replyText);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			int messageId=Integer.parseInt(request.getParameter("messageId"));
			request.getRequestDispatcher(Constants.getMessageURL(messageId)).forward(request, response);
		}
	}

}
