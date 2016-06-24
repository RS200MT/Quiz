package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import Models.Constants;
import Models.DBObject;
import Models.User;

/**
 * Servlet implementation class GetProfile
 */
@WebServlet("/GetProfile")
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
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
			String userName = request.getParameter(Constants.GET_PROFILE_USER_NAME_HIDDEN);
			String addFriend = request.getParameter(Constants.GET_PROFILE_ADD_FRIEND);
			String sendMessage = request.getParameter(Constants.GET_PROFILE_SEND_MESSAGE);
			String unfriend = request.getParameter(Constants.GET_PROFILE_UNFRIEND);
			//If add friend was pusher
			if(addFriend!=null) {
				db.addFriendRequest(sessionUser.getUserName(), userName);
				sessionUser.addFriend(userName);
//				request.getRequestDispatcher(Constants.getUserProfileURL(userName)).forward(request, response);
			}
			//If send message was pushed
			if(sendMessage != null) {
				String messageText = request.getParameter(Constants.GET_PROFILE_MESSAGE_TEXT);
				if(messageText!=null && !messageText.equals("") && !messageText.equals("Message Text")) {
					db.addSentMessage(sessionUser.getUserName(), userName, messageText);
				}
			}
			//If unfriend was pushed
			if(unfriend != null) {
				db.removeFriend(sessionUser.getUserName(), userName);
				sessionUser.removeFriend(userName);
				
			}
			request.getRequestDispatcher(Constants.getUserProfileURL(userName)).forward(request, response);

		}
		
		
		
		
		
	}

}
