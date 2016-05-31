package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.attribute.standard.DateTimeAtCompleted;

import com.sun.jmx.snmp.Timestamp;

public class DBObject {
	public static final String MYSQL_USERNAME = DBInfo.MYSQL_USERNAME;
	public static final String MYSQL_PASSWORD = DBInfo.MYSQL_PASSWORD;
	public static final String MYSQL_DATABASE_SERVER = DBInfo.MYSQL_DATABASE_SERVER;
	public static final String MYSQL_DATABASE_NAME = DBInfo.MYSQL_DATABASE_NAME;
	public static final String TABLE_USERS = "users";
	
	public DBObject() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
	
	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet getResultSet(String query) {
		ResultSet result = null;
		Connection conn = getConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(conn);
		return result;
	}
	
	
	
	private void executeUpdate(String query) {
		Connection conn = getConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection(conn);
	}
	

	/**
	 * Adds new user into users table. Uses executeUpdate;
	 * @param u
	 */
	public boolean addUser(String name, String email, String password) {
		Connection conn = getConnection();
		if(userAlreadyExists(name, email)) {
			closeConnection(conn);
			return false;
		} else {
			java.util.Date date= new java.util.Date();
			String query = "INSERT INTO " + TABLE_USERS + " VALUES " + 
							"(" + name + ", "+email + ", " + password + ", " + new Timestamp(date.getTime()) +");";
			executeUpdate(query);
			closeConnection(conn);
			return true;
		}
	}
	

	/**
	 * Checks if user with given name or email already exists;
	 * @param name
	 * @param email
	 * @return boolean
	 */
	private boolean userAlreadyExists(String name, String email) {
		String queryForName = "SELECT * FROM " + TABLE_USERS + "WHERE user_name is " + name;
		String queryForEmail = "SELECT * FROM " + TABLE_USERS + "WHERE email is " + email;
		ResultSet r1 = getResultSet(queryForName);
		ResultSet r2 = getResultSet(queryForEmail);
		try {
			if(!r1.next() || !r2.next()){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return true;
	}

	public String getPasswordHash(String userName) throws SQLException {
		String query = " Select * from users where user_name = \"" + userName + "\"";
		ResultSet rs = getResultSet(query);
		return rs.getString(4);

	}
	
	private void example() {
		Connection conn = getConnection();
		
		closeConnection(conn);
	}
}
