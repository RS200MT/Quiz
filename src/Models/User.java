package Models;

public class User {
	private int id;
	private String userName;
	private String email;
	private String regDate;
	private int quizNumber;
	
	public User(int id, String userName, String email, String regDate, int quizNumber) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.regDate = regDate;
		this.quizNumber = quizNumber;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getRegDate() {
		return this.regDate;
	}
	
	public int getQuizNumber() {
		return this.quizNumber;
	}
	
	public void increaseQuizNumber() {
		this.quizNumber++;
	}
}
