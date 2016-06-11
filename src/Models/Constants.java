package Models;

public class Constants {
	// Pages
	public static final String P_HOMEPAGE = "index.jsp";
	public static final String P_NEW_ACCOUNT = "newAccount.jsp";
	public static final String P_LOGIN = "Login.jsp"; 
	public static final String P_REGISTER = "newAccount.jsp";
	public static final String P_ADD_QUIZ = "addQuiz.jsp";
	public static final String P_ADD_QUESTION = "addQuestion.jsp";
	
	// Servlets
	public static final String S_LOGOUT = "Logout";
	public static final String S_LOGIN = "Login";
	public static final String S_REGISTER = "addUser";
	public static final String S_ADD_QUIZ = "addQuiz";
	public static final String S_QUIZING = "Quizing";
	
	// 
	
	// Button values
	public static final String B_LOGOUT = "Log out";
	public static final String B_LOGIN = "Log in";
	public static final String B_REGISTER = "Register";
	public static final String B_ADD_QUIZ = "Create Quiz";
	public static final String B_MY_QUIZES = "My quizes";
	
	// Parameter names and IDs
	public static final String LOGIN_USERNAME = "login_username";
	public static final String LOGIN_PASSWORD = "login_password";
	public static final String REGISTER_USERNAME = "register_username";
	public static final String REGISTER_EMAIL = "register_email";
	public static final String REGISTER_PASSWORD = "register_password";
	public static final String ADD_QUIZ_TITLE = "quiz_title";
	public static final String ADD_QUESTION_QUESTION = "question";
	public static final String ADD_QUESTION_ANSWER = "answer";
	public static final String ADD_QUESTION_TYPE = "type";
	public static final String ADD_QUESTION_QUIZ_ID = "quizId";
	public static final String ADD_QUESTION_IMAGE = "image";
	public static final String ADD_QUESTION_POSSIBLE_ANSWER = "possibleAnswer";
	public static final String ADD_QUESTION_DONE_QUIZ = "doneQuiz";
	public static final String ADD_QUESTION_NEXT_QUESTION = "nextQuestion";
	public static final String QUIZINT_ID = "id";
	public static final String QUIZINT_SINGLE_QUESTION = "singleQuestion";
	
	// Errors and descriptions
	public static final String LOGIN_USER_NOT_FOUND = "The username was not found!";
	public static final String LOGIN_INCORRECT_PASSWORD = "The password was incorrect!";
	public static final String REGISTER_USERNAME_EXISTS = "The username or email already exists!";
	
	// Attributes
	public static final String ATTR_FAILED_LOGIN = "FAILED_LOGIN";
	public static final String ATTR_USERNAME_EXISTS = "USERENAME_EXISTS";
	public static final String ATTR_USER = "USER";
	public static final String ATTR_QUIZ_ID_FOR_QUESTION = "quizId";
	public static final String ATTR_SESSION_QUIZ = "quizAttr";
	
	
	
	
	// WASKA's constants new;
	public static final String SITE_URL = "http://localhost:8080/QuizWebsite";
	
	public static final String INDEX = "index.jsp";
	public static final String INDEX_DO = "do";	//index.jsp parameter
	public static final String INDEX_HASHMAP = "pagesHashMap";	//hashmap attr in servlet context
	// hashSet pages for index.jsp?do=??
	public static final String INDEX_DO_HOMEPAGE = "homePage";	
	public static final String INDEX_DO_HOMEPAGE_TITLE = "Home Page";	
	public static final String INDEX_DO_PROFILE_INFO = "profileInfo";	
	public static final String INDEX_DO_PROFILE_INFO_TITLE = "Log in";
	public static final String INDEX_DO_REGISTER = "newAccount";	
	public static final String INDEX_DO_REGISTER_TITLE = "Register new account";
	public static final String INDEX_DO_ADD_QUIZ = "addQuiz";
	public static final String INDEX_DO_ADD_QUIZ_TITLE = "Create new quiz";
	public static final String INDEX_DO_ADD_QUESTION = "addQuestion";
	public static final String INDEX_DO_ADD_QUESTION_TITLE = "Add question";

	public static final String INDEX_DO_MY_QUIZES = "myQuizes";
	public static final String INDEX_DO_MY_QUIZES_TITLE = "my Quizes";

	public static final String INDEX_DO_QUIZ_PAGE = "QuizPage";
	public static final String INDEX_DO_QUIZ_PAGE_TITLE = "Quiz page";
	
	
	
	public static String getAction(String action) {
		return INDEX + "?" + INDEX_DO + "=" + action;
	}
}
