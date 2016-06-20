package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Questions.FillInBlankQuestion;
import Questions.MultipleChoiceQuestion;
import Questions.PictureQuestion;
import Questions.QuestionResponse;
import javafx.util.Pair;

public class DBObject {
	public static final String ATTR_DB = "ATTR_DB";
	public static final String MYSQL_USERNAME = DBInfo.MYSQL_USERNAME;
	public static final String MYSQL_PASSWORD = DBInfo.MYSQL_PASSWORD;
	public static final String MYSQL_DATABASE_SERVER = DBInfo.MYSQL_DATABASE_SERVER;
	public static final String MYSQL_DATABASE_NAME = DBInfo.MYSQL_DATABASE_NAME;

	public static final String TABLE_USERS = "users";
	public static final String TABLE_QUIZES = "quizes";
	public static final String TABLE_QUESTIONS = "questions";
	public static final String TABLE_QUIZ_LOGS = "quiz_logs";
	public static final String TABLE_CORRECT_ANSWERS = "correct_answers";
	public static final String TABLE_QUESTION_IMAGES = "question_images";
	public static final String TABLE_MULTIPLE_CHOICES = "multiple_choices";
	public static final String TABLE_FRIENDS = "friends";

	public DBObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates and returns connection with the database
	 * 
	 * @return {@link Connection}
	 */
	private Connection getConnection() {
		try {
			String connect = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + "/" + MYSQL_DATABASE_NAME;
			return DriverManager.getConnection(connect, MYSQL_USERNAME, MYSQL_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("MySQL user password server or db name is incorrect!");
			return null;
		}
	}

	/**
	 * Closes given connection
	 * 
	 * @param conn
	 */
	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns result set generated by executing given query;
	 * 
	 * @param query
	 * @return {@link ResultSet}
	 */
	private ResultSet getResultSet(String query, Connection conn) {
		ResultSet result = null;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Executes update queries, that is queries which cause changes in tables of
	 * the database;
	 * 
	 * @param query
	 * @throws SQLException
	 */
	private int executeUpdate(String query, Connection conn) {
		int id = 0;
		try {
			Statement stmt1;
			stmt1 = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt1.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt1.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 
	 * Checks if user with given name or email already exists; If so, returns
	 * false, if such a user doesn't exist, adds the new user into users table.
	 * Uses executeUpdate; Method receives hashed password;
	 * 
	 * @param name
	 * @param email
	 * @param hashedPassword
	 * @return boolean
	 */
	public boolean addUser(String username, String email, String hashedPassword) {
		Connection conn = getConnection();
		if (userAlreadyExists(username, email, conn)) {
			System.out.println("User already exists");
			closeConnection(conn);
			return false;
		} else {
			System.out.println("User added successfully");
			String query = "INSERT INTO " + TABLE_USERS + " (user_name, email, password) VALUES " + "('" + username
					+ "', '" + email + "', '" + hashedPassword + "');";
			executeUpdate(query, conn);
			closeConnection(conn);
			return true;
		}
	}

	/**
	 * Checks if user with given name or email already exists;
	 * 
	 * @param name
	 * @param email
	 * @return boolean
	 */
	private boolean userAlreadyExists(String name, String email, Connection conn) {
		String query = "SELECT * FROM " + TABLE_USERS + " WHERE user_name = '" + name + "' or email = '" + email
				+ "' limit 1;";
		ResultSet r = getResultSet(query, conn);
		try {
			if (r.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Get hashed password for user with given name;
	 * 
	 * @param userName
	 * @return {@link String}
	 * @throws SQLException
	 */
	public String getPasswordHash(String userName) {
		String result = null;
		Connection conn = getConnection();
		String query = "Select * from " + TABLE_USERS + " where user_name = '" + userName + "';";
		ResultSet rs = getResultSet(query, conn);
		try {
			if (rs.next())
				result = rs.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(conn);
		return result;
	}

	/**
	 * Gets info of the user with given name
	 * 
	 * @param userName
	 * @return {@link HashMap}
	 */
	public HashMap<String, Object> getUserInfo(String userName) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Connection conn = getConnection();
		String query = "Select * from " + TABLE_USERS + " where user_name = '" + userName + "';";
		ResultSet rs = getResultSet(query, conn);
		try {
			if (rs.next()) {
				result.put("id", rs.getInt("id"));
				result.put("email", rs.getString("email"));
				result.put("reg_date", rs.getString("reg_date"));
				result.put("quizes_written", rs.getInt("quizes_written"));
				result.put("type", rs.getInt("type"));
			} else {
				System.out.println("User was not found in database!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(conn);
		return result;
	}

	/**
	 * Gets quiz with given id from database and return it; Returns null if quiz
	 * with given id was not found;
	 * 
	 * @param id
	 * @param singleQuestion
	 * @return Quiz
	 * @throws SQLException
	 */
	public Quiz getQuizById(int id, int singleQuestion) throws SQLException {
		Connection conn = getConnection();
		String query = "SELECT quizes.*, users.user_name FROM " + TABLE_QUIZES + " quizes left join " + TABLE_USERS
				+ " users on quizes.author = users.id WHERE quizes.id = " + id + " limit 1;";
		ResultSet rs = getResultSet(query, conn);
		if (rs.next()) {
			String title = rs.getString("title");
			String description = rs.getString("description");
			String author = rs.getString("user_name");
			String createTime = rs.getString("create_time");
			int timesWritten = rs.getInt("times_written");
			boolean randomized = rs.getInt("randomize") == 1;
			boolean immediateCorrection = rs.getInt("immediate_correction") == 1;
			ArrayList<Question> questions = getQuestionsForQuiz(id, conn);
			boolean displaySingleQuestion = singleQuestion == 1;
			return new Quiz(id, title, description, author, createTime, timesWritten, randomized, immediateCorrection, questions, displaySingleQuestion);
		} else {
			System.out.println("Quiz not found!");
			return null;
		}
	}

	/**
	 * Get questions for quiz with given id; Assembles question infos from
	 * different tables depending on type of the question; Returns null if quiz
	 * contains no questions;
	 * 
	 * @param id
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Question> getQuestionsForQuiz(int id, Connection conn) throws SQLException {
		String query = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE quiz_id = " + id + ";";
		ResultSet rs = getResultSet(query, conn);
		if (!rs.isBeforeFirst()) {
			return null;
		}
		ArrayList<Question> result = new ArrayList<Question>();
		while (rs.next()) {
			int qId = rs.getInt("id");
			result.add(getQuestionById(qId, conn));
		}
		return result;
	}

	private Question getQuestionById(int id, Connection conn) throws SQLException {
		String query = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE id = " + id + " limit 1;";
		ResultSet rs = getResultSet(query, conn);
		if (rs.next()) {
			int quizId = rs.getInt("quiz_id");
			String question = rs.getString("question");
			int type = rs.getInt("q_type");
			if (type == QuestionResponse.getType()) {
				return new QuestionResponse(question, getCorrectAnswers(id, conn));
			} else if (type == FillInBlankQuestion.getType()) {
				return new FillInBlankQuestion(question, getCorrectAnswers(id, conn));
			} else if (type == MultipleChoiceQuestion.getType()) {
				return new MultipleChoiceQuestion(question, getCorrectAnswers(id, conn), getPossibleAnswers(id, conn));
			} else if (type == PictureQuestion.getType()) {
				return new PictureQuestion(question, getCorrectAnswers(id, conn), getImageURL(id, conn));
			}
		}
		return null;
	}

	private String getImageURL(int id, Connection conn) throws SQLException {
		String imageURL = "SELECT * FROM " + TABLE_QUESTION_IMAGES + " WHERE question_id = " + id + ";";
		ResultSet rs = getResultSet(imageURL, conn);
		if (rs.next()) 
			return rs.getString("image_url");
		return "";
	}

	private ArrayList<String> getPossibleAnswers(int id, Connection conn) throws SQLException {
		String getPossibleAnswers = "SELECT * FROM " + TABLE_MULTIPLE_CHOICES + " WHERE question_id = " + id + ";";
		ResultSet possibleAnswers = getResultSet(getPossibleAnswers, conn);
		ArrayList<String> possibleAnswersList = new ArrayList<String>();
		while (possibleAnswers.next()) {
			String nextPossAnswer = possibleAnswers.getString("answer");
			possibleAnswersList.add(nextPossAnswer);
		}
		return possibleAnswersList;
	}

	/**
	 * 
	 * @param id
	 * @param conn2
	 * @return 
	 * @throws SQLException
	 */
	private ArrayList<String> getCorrectAnswers(int id, Connection conn) throws SQLException {
		String getCorrectAnswers = "SELECT * FROM " + TABLE_CORRECT_ANSWERS + " WHERE question_id = " + id + ";";
		ResultSet correctAnswers = getResultSet(getCorrectAnswers, conn);
		if (!correctAnswers.isBeforeFirst()) {
			throw new Error("No correct answers for this question in database");
		}
		ArrayList<String> result = new ArrayList<String>();
		while (correctAnswers.next()) {
			String nextAnswer = correctAnswers.getString("correct_answer");
			result.add(nextAnswer);
		}
		return result;
	}

	/**
	 * Gets specific info for different types of questions;
	 * 
	 * @param info
	 * @param qId
	 * @param qType
	 * @throws SQLException
	 */
//	private void getSpecificQuestionInfo(ArrayList<Object> info, int qId, int qType) throws SQLException {
//		Connection conn = getConnection();
//		if (qType == QuestionType.MultipleChoice.ordinal()) {
//			String getPossibleAnswers = "SELECT * FROM " + TABLE_MULTIPLE_CHOICES + " WHERE question_id = " + qId + ";";
//			ResultSet possibleAnswers = getResultSet(getPossibleAnswers, conn);
//			ArrayList<String> possibleAnswersList = new ArrayList<String>();
//			while (possibleAnswers.next()) {
//				String nextPossAnswer = possibleAnswers.getString("answer");
//				possibleAnswersList.add(nextPossAnswer);
//			}
//			info.add(2, possibleAnswersList);
//		} else if (qType == QuestionType.PictureResponse.ordinal()) {
//			String imageURL = "SELECT * FROM " + TABLE_QUESTION_IMAGES + " WHERE question_id = " + qId + ";";
//			ResultSet url = getResultSet(imageURL, conn);
//			if (url.next()) {
//				info.add(2, url.getString("image_url"));
//			}
//		}
//		conn.close();
//	}

	/**
	 * Get several most popular quizzes in the database; If there are not as
	 * many quizzes in database as n, returns all the quizzes sorted according
	 * to popularity in descending order;
	 * 
	 * @param n
	 * @return {@link ArrayList}
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getPopularQuizes(int n) throws SQLException {
		ArrayList<Quiz> popularQuizes = new ArrayList<Quiz>();
		Connection conn = getConnection();
		String query = "SELECT * FROM " + TABLE_QUIZES + " ORDER BY times_written DESC LIMIT " + n + ";";
		ResultSet rs = getResultSet(query, conn);
		if (!rs.isBeforeFirst())
			return null;
		while (rs.next()) {
			popularQuizes.add(getQuizById(rs.getInt("id")));
		}
		closeConnection(conn);
		return popularQuizes;
	}

	public ArrayList<Quiz> getRecentQuizesForUser(int userID, int n) throws SQLException {
		ArrayList<Quiz> recentQuizesForUser = new ArrayList<Quiz>();
		Connection conn = getConnection();
		String query = "select * from " + TABLE_QUIZ_LOGS + " where user_id = " + userID + " order by start_time limit " + n +";";
		ResultSet rs = getResultSet(query, conn);
		if (!rs.isBeforeFirst())
			return null;
		while (rs.next()) {
			recentQuizesForUser.add(getQuizById(rs.getInt("id")));
		}
		closeConnection(conn);
		System.out.println(recentQuizesForUser.size());
		return recentQuizesForUser;
	}

	/**
	 * Returns list of given number of recently created quizzes; If there are
	 * less than n quizzes in database, returns all the quizzes in the database;
	 * 
	 * @param n
	 * @return {@link ArrayList}
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getRecentQuizes(int n) throws SQLException {
		ArrayList<Quiz> recentQuizes = new ArrayList<Quiz>();
		Connection conn = getConnection();
		String query = "SELECT * FROM " + TABLE_QUIZES + " ORDER BY create_time DESC LIMIT " + n + ";";
		ResultSet rs = getResultSet(query, conn);
		if (!rs.isBeforeFirst())
			return null;
		while (rs.next()) {
			recentQuizes.add(getQuizById(rs.getInt("id")));
		}
		closeConnection(conn);
		return recentQuizes;
	}

	
	public void addToQuizLog(int userID, Quiz quiz){
		Connection conn = getConnection();
		String query = "Insert into " + TABLE_QUIZ_LOGS + " values ('" + userID +"', '" + quiz.getID() +
				"', '" + quiz.getScore() + "', '" + quiz.getStartTime() + "', '" + quiz.getSpentTime() +"');";
		executeUpdate(query, conn);
		closeConnection(conn);
	}
	

	// This function insert quiz in database
	public int addQuiz(String title, String description, boolean isRandomized, boolean isImmediateCorrection,
			int authorId) {
		int random = isRandomized ? 1 : 0;
		int immediateCorrection = isImmediateCorrection ? 1 : 0;
		Connection conn = getConnection();
		String query = "INSERT INTO " + TABLE_QUIZES
				+ " (title, description, author, randomize, immediate_correction) VALUES ('" + title + "', '"
				+ description + "', " + authorId + ", " + random + ", " + immediateCorrection + ");";
		int quizId = executeUpdate(query, conn);
		closeConnection(conn);
		return quizId;
	}

	// This function gets quizId and question and inserts this question in
	// database for the quiz
	public int addQuestionToQuiz(int quizId, Question question, int questionType) {
		Connection conn = getConnection();
		String quest = question.getQuestion();
		ArrayList<String> answers = question.getAnswers();
		int questionId = executeUpdate("INSERT INTO " + TABLE_QUESTIONS + " (quiz_id, question, q_type) VALUES ("
				+ quizId + ", '" + quest + "', " + questionType + ");", conn);
		for (int i = 0; i < answers.size(); i++)
			executeUpdate("INSERT INTO " + TABLE_CORRECT_ANSWERS + " (question_id, correct_answer) VALUES ("
					+ questionId + ", '" + answers.get(i) + "');", conn);
		if (questionType == MultipleChoiceQuestion.getType()) {
			ArrayList<String> possibleAnswers = question.getAdditionalData();
			if (possibleAnswers != null) {
				for (int i = 0; i < possibleAnswers.size(); i++)
					executeUpdate("INSERT INTO " + TABLE_MULTIPLE_CHOICES + " (question_id, answer) VALUES ("
							+ questionId + ", '" + possibleAnswers.get(i) + "');", conn);
			}
		} else if (questionType == PictureQuestion.getType()) {
			ArrayList<String> additionalData = question.getAdditionalData();
			if (additionalData != null && additionalData.size() == 1)
				executeUpdate("INSERT INTO " + TABLE_QUESTION_IMAGES + " (question_id, image_url) VALUES (" + questionId
						+ ", '" + additionalData.get(0) + "');", conn);
		}
		closeConnection(conn);
		return questionId;
	}

	public ArrayList<Pair<String, Integer>> getQuizesListForUser(int userId) {
		ArrayList<Pair<String, Integer>> res = new ArrayList<Pair<String, Integer>>();
		Connection conn = getConnection();
		String getUserQuizes = "SELECT * FROM " + TABLE_QUIZES + " where author = " + userId + ";";
		ResultSet userQuizes = getResultSet(getUserQuizes, conn);
		try {
			while (userQuizes.next()) {
				String quizTitle = userQuizes.getString("title");
				int quizId = userQuizes.getInt("id");
				res.add(new Pair<String, Integer>(quizTitle, quizId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection(conn);
		return res;
	}

	public User getUserByUserName(String passed_username) {
		Connection conn = getConnection();
		String query = "Select * from " + TABLE_USERS + " where user_name = '" + passed_username + "';";
		ResultSet rs = getResultSet(query, conn);
		User result = null;
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String regDate = rs.getString("reg_date");
				int quizesWritten = rs.getInt("quizes_written");
				int type = rs.getInt("type");
				result = new User(id, passed_username, email, regDate, quizesWritten, type, null);
			} else {
				System.out.println("User was not found in database!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result.addFriends(getUserFriends(conn, result.getId()));
		closeConnection(conn);
		return result;
	}

	private ArrayList<Pair<Integer, String>> getUserFriends(Connection conn, int id) {
		ArrayList<Pair<Integer, String>> friends = new ArrayList<Pair<Integer, String>>();
		String query = "Select * from " + TABLE_FRIENDS + " where user1_id = " + id + " or user2_id = " + id + ";";
		// TO DO TODO
		return null;
	}

}
