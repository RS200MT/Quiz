package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Constants;
import Models.DBObject;
import Models.User;
import Questions.Question;
import Questions.Question.QuestionType;

/**
 * Servlet implementation class addQuestion
 */
@WebServlet("/addQuestion")
public class addQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addQuestion() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// gadmogvecema tipi, kitxva, pasuxi,
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter(Constants.ADD_QUESTION_QUIZ_ID));
		String question = request.getParameter(Constants.ADD_QUESTION_QUESTION);
		int type = Integer.parseInt(request.getParameter(Constants.ADD_QUESTION_TYPE));
		ArrayList<String> answers = new ArrayList<String>();
		int i = 1;
		while (true) {
			String answer = request.getParameter(Constants.ADD_QUESTION_ANSWER + i);
			if (answer == null)
				break;
			answers.add(answer);
			i++;
		}
		ArrayList<Object> questionInfo = new ArrayList<Object>();
		questionInfo.add(question);
		questionInfo.add(answers);
		Question q = null;
		if (type == QuestionType.PictureResponse.ordinal()) {
			questionInfo.add(request.getParameter(Constants.ADD_QUESTION_IMAGE));
		} else if (type == QuestionType.MultipleChoice.ordinal()) {
			ArrayList<String> arr = new ArrayList<String>();
			for (int j = 1; j <= 4; j++) {
				arr.add(request.getParameter(Constants.ADD_QUESTION_POSSIBLE_ANSWER + j));
			}
			questionInfo.add(arr);
		}
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		q = new Question(QuestionType.values()[type], questionInfo);
		obj.addQuestionToQuiz(quizId, q);
		if (request.getParameter(Constants.ADD_QUESTION_NEXT_QUESTION) != null) {
			request.setAttribute(Constants.ATTR_QUIZ_ID_FOR_QUESTION, quizId);
			request.getRequestDispatcher(Constants.P_ADD_QUESTION).forward(request, response);
		} else
			request.getRequestDispatcher(Constants.P_HOMEPAGE).forward(request, response);

	}

}
