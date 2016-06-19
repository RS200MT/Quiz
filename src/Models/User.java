package Models;

import java.util.HashMap;

public class User {
	private int id;
	private String userName;
	private String email;
	private String regDate;
	private int quizNumber;
	private int type;

	public User(String userName, DBObject db) {
		this.userName = userName;
		HashMap<String, Object> userInfo = db.getUserInfo(this.userName);
		this.id = (int)userInfo.get("id");
		this.email = (String)userInfo.get("email");
		this.regDate = (String)userInfo.get("reg_date");
		this.quizNumber = (int)userInfo.get("quizes_written");
		this.type = (int)userInfo.get("type");
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
	
	public int getType() {
		return this.type;
	}

	public void increaseQuizNumber() {
		this.quizNumber++;
	}

	
}
