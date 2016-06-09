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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// gadmogvecema tipi, kitxva, pasuxi,
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter("quiz_id"));
		String question = request.getParameter(Constants.ADD_QUESTION_QUESTION);
 		int type = Integer.parseInt(request.getParameter(Constants.ADD_QUESTION_TYPE));
 		String img_url = request.getParameter(Constants.ADD_QUESTION_IMAGE);
 		ArrayList<String> answers = new ArrayList<String>();
 		int i = 1;
 		while(true){
 			String answer = request.getParameter(Constants.ADD_QUESTION_ANSWER + i);
 			if(answer == null) break;
 			answers.add(answer);
 			i++;
 		}
 		ArrayList<Object> questionInfo = new ArrayList<Object>();
 		questionInfo.add(question);
 		questionInfo.add(answers);
 		Question q = null;
 		if(img_url != null) questionInfo.add(img_url);
		DBObject obj = (DBObject) getServletContext().getAttribute(DBObject.ATTR_DB);
 		switch(type){
	 		case 1 : q = new Question(QuestionType.QuestionResponse,questionInfo);
	 				break;
	 		case 2 : q = new Question(QuestionType.FillInBlank,questionInfo);
	 				break;
	 		case 3 : q = new Question(QuestionType.MultipleChoice,questionInfo);
	 				break;
	 		case 4: q = new Question(QuestionType.PictureResponse,questionInfo);
	 				break;
 		}
 		obj.addQuestionToQuiz(quizId, q);
 		
	}

}
