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
import Models.Question;
import Questions.FillInBlankQuestion;
import Questions.MultipleChoiceQuestion;
import Questions.PictureQuestion;
import Questions.QuestionResponse;

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
		request.getRequestDispatcher(Models.Constants.INDEX).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter(Constants.ADD_QUESTION_QUIZ_ID));
		int questionType = Integer.parseInt(request.getParameter(Constants.ADD_QUESTION_TYPE));
		Question curQuestion = getQuestion(request, questionType);
		if (curQuestion == null)
			System.out.println("Error! curQuestion == null");
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
		obj.addQuestionToQuiz(quizId, curQuestion, questionType);
		if (request.getParameter(Constants.ADD_QUESTION_NEXT_QUESTION) != null) {
			request.setAttribute(Constants.ATTR_QUIZ_ID_FOR_QUESTION, quizId);
			request.getRequestDispatcher(Constants.getAction(Constants.INDEX_DO_ADD_QUESTION)).forward(request,
					response);
		} else
			request.getRequestDispatcher(Constants.INDEX).forward(request, response);
	}

	private Question getQuestion(HttpServletRequest request, int type) {
		Question result = null;
		String question = request.getParameter(Constants.ADD_QUESTION_QUESTION);
		ArrayList<String> answers = getAnswers(request);
		if (type == PictureQuestion.getType()) {
			String imageURL = request.getParameter(Constants.ADD_QUESTION_IMAGE);
			result = new PictureQuestion(question, answers, imageURL);
		} else if (type == MultipleChoiceQuestion.getType()) {
			ArrayList<String> possibleAnswers = new ArrayList<String>();
			for (int i = 1; i <= 4; i++)
				possibleAnswers.add(request.getParameter(Constants.ADD_QUESTION_POSSIBLE_ANSWER + i));
			result = new MultipleChoiceQuestion(question, answers, possibleAnswers);
		} else if (type == FillInBlankQuestion.getType())
			result = new FillInBlankQuestion(question, answers);
		else
			result = new QuestionResponse(question, answers);
		return result;
	}

	private ArrayList<String> getAnswers(HttpServletRequest request) {
		ArrayList<String> answers = new ArrayList<String>();
		int i = 1;
		while (true) {
			String answer = request.getParameter(Constants.ADD_QUESTION_ANSWER + i);
			if (answer == null)
				break;
			answers.add(answer);
			i++;
		}
		return answers;
	}

}
