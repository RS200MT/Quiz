package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

	/* memogni es aq ar unda iyos;
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	public static String getHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes());
			return hexToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	*/
}
