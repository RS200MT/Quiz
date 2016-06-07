package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Questions.Question;

public class DBObject {
	public static final String ATTR_DB = "ATTR_DB";
	public static final String MYSQL_USERNAME = DBInfo.MYSQL_USERNAME;
	public static final String MYSQL_PASSWORD = DBInfo.MYSQL_PASSWORD;
	public static final String MYSQL_DATABASE_SERVER = DBInfo.MYSQL_DATABASE_SERVER;
	public static final String MYSQL_DATABASE_NAME = DBInfo.MYSQL_DATABASE_NAME;

	public static final String TABLE_USERS = "users";
	public static final String TABLE_QUIZES = "quizes";
	public static final String TABLE_QUIZ_LOGS = "quiz_logs";

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
	 */
	private void executeUpdate(String query, Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE " + MYSQL_DATABASE_NAME + ";");
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <<<<<<< HEAD
	 * 
	 * Checks if user with given name or email already exists; If so, returns
	 * false, if such a user doesn't exist, adds the new user into users table.
	 * Uses executeUpdate; Method receives hashed password;
	 *
	 * ======= Checks if user with given name or email already exists; Is so,
	 * returns false, if such a user doesn't exist, adds the new user into users
	 * table. Uses executeUpdate; Method receives hashed password;
	 * 
	 * >>>>>>> f042ebe08aae1506ef170d83571f76e95fff8096
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

	public HashMap<String, Object> getUserInfo(String userName, int id, String email, String regDate, int quizNumber,
			int type) {
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

	public ArrayList<Quiz> getRecentQuizes(int numQuizes) throws SQLException {
		ArrayList<Quiz> res = new ArrayList<Quiz>();
		Connection conn = getConnection();
		Statement stm = conn.createStatement();
		String query = "Select * from " + TABLE_QUIZES + "order by create_time desc limit " + numQuizes;
		ResultSet rs = getResultSet(query, conn);
		while (rs.next()) {
			int id = rs.getInt(0);
			String title = rs.getString(1);
			String author = rs.getString(2);
			String date = rs.getTimestamp(3).toString();
			int timesWritten = rs.getInt(4);
			ArrayList<Question> questions = getQuestionsForQuiz(id, conn);
			Quiz q = new Quiz(id, author, questions, timesWritten);
			res.add(q);
		}
		return res;

	}

	/**
	 * Returns demanded amount of most popular quizes
	 * 
	 * @param numQuizes
	 * @throws SQLException
	 */
	public ArrayList<Quiz> getPopularQuizes(int numQuizes) throws SQLException {
		ArrayList<Quiz> res = new ArrayList<Quiz>();
		Connection conn = getConnection();
		Statement stm = conn.createStatement();
		// String query = "SELECT * FROM " + TABLE_QUIZ_LOGS +
		// "GROUP BY 'quiz_id' ORDER BY COUNT('user_id') LIMIT " + numQuizes +
		// ";";

		String query = "SELECT 'quiz_id' FROM " + TABLE_QUIZES + " ORDER BY 'times_written' DESC LIMIT " + numQuizes;
		ResultSet rs = getResultSet(query, conn);
		while (rs.next()) {
			int id = rs.getInt(0);
			String title = rs.getString(1);
			String author = rs.getString(2);
			String date = rs.getTimestamp(3).toString();
			int timesWritten = rs.getInt(4);
			ArrayList<Question> questions = getQuestionsForQuiz(id, conn);
			Quiz q = new Quiz(id, author, questions, timesWritten);
			res.add(q);
		}
		closeConnection(conn);
		return res;
	}

	private ArrayList<Question> getQuestionsForQuiz(int id, Connection conn) {
		try {
			Statement stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// This function insert quiz in database
	public void addQuiz(String title, int id) {
		Connection conn = getConnection();
		String query = "INSERT INTO " + TABLE_QUIZES + " (title, author) VALUES ('" + title + "', '" + id + "');";
		executeUpdate(query, conn);
		closeConnection(conn);
	}

	private void example() {
		Connection conn = getConnection();

		closeConnection(conn);
	}

}
