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
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
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
			String unfriend = request.getParameter(Constants.GET_PROFILE_UNFRIEND);
			String acceptFriendRequest = request.getParameter(Constants.GET_PROFILE_ACCEPT_FRIEND_REQUEST);
			String declineFriendRequest = request.getParameter(Constants.GET_PROFILE_DECLINE_FRIEND_REQUEST);
			//If "Add Friend" was pushed
			if(addFriend!=null) {
				try {
					db.addFriendRequest(sessionUser.getUserName(), userName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sessionUser.addFriend(userName);
			}
			//If unfriend was pushed
			if(unfriend != null) {
				try {
					db.removeFriend(sessionUser.getUserName(), userName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sessionUser.removeFriend(userName);
				
			}
			//If "accept friend request" was pushed
			if(acceptFriendRequest != null) {
				try {
					db.acceptFriendRequest(sessionUser.getId(), db.getUserIdByUserName(userName));
					sessionUser.addFriend(userName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//If "decline friend request" was pushed
			if(declineFriendRequest != null) {
				try {
					db.removeFriend(sessionUser.getUserName(), userName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher(Constants.getUserProfileURL(userName)).forward(request, response);
		}
	}

}
