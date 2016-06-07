package Models;

public class Constants {
	// Pages
	public static final String P_HOMEPAGE = "index.jsp";
	public static final String P_NEW_ACCOUNT = "newAccount.jsp";
	public static final String P_LOGIN = "Login.jsp"; 
	public static final String P_REGISTER = "newAccount.jsp";
	
	// Servlets
	public static final String S_LOGOUT = "Logout";
	public static final String S_LOGIN = "Login";
	public static final String S_REGISTER = "addUser";
	
	// 
	
	// Button values
	public static final String B_LOGOUT = "Log out";
	public static final String B_LOGIN = "Log in";
	public static final String B_REGISTER = "Register";
	
	// Parameter names and IDs
	public static final String LOGIN_USERNAME = "login_username";
	public static final String LOGIN_PASSWORD = "login_password";
	public static final String REGISTER_USERNAME = "register_username";
	public static final String REGISTER_EMAIL = "register_email";
	public static final String REGISTER_PASSWORD = "register_password";
	
	// Errors and descriptions
	public static final String LOGIN_USER_NOT_FOUND = "The username was not found!";
	public static final String LOGIN_INCORRECT_PASSWORD = "The password was incorrect!";
	public static final String REGISTER_USERNAME_EXISTS = "The username or email already exists!";
	
	// Attributes
	public static final String ATTR_FAILED_LOGIN = "FAILED_LOGIN";
	public static final String ATTR_USERNAME_EXISTS = "USERENAME_EXISTS";
	public static final String ATTR_USER = "USER";
}
