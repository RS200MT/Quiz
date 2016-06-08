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
		HashMap<String, Object> userInfo = db.getUserInfo(this.userName, this.id, this.email, this.regDate, this.quizNumber, this.type);
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

	/*
	 * memogni es aq ar unda iyos; public static String hexToString(byte[]
	 * bytes) { StringBuffer buff = new StringBuffer(); for (int i = 0; i <
	 * bytes.length; i++) { int val = bytes[i]; val = val & 0xff; // remove
	 * higher bits, sign if (val < 16) buff.append('0'); // leading 0
	 * buff.append(Integer.toString(val, 16)); } return buff.toString(); }
	 * 
	 * public static String getHash(String password) { try { MessageDigest md =
	 * MessageDigest.getInstance("SHA"); md.update(password.getBytes()); return
	 * hexToString(md.digest()); } catch (NoSuchAlgorithmException e) {
	 * e.printStackTrace(); } return ""; }
	 */
}
