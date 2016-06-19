package Models;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class User {
	private int id;
	private String userName;
	private String email;
	private String regDate;
	private int quizNumber;
	private int type;
	private ArrayList<Pair<Integer, String>> friends;

	public User(int id, String userName, String email, String regDate, int quizNumber, int type,
			ArrayList<Pair<Integer, String>> friends) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.regDate = regDate;
		this.quizNumber = quizNumber;
		this.type = type;
		if (friends != null)
			this.friends = friends;
		else
			friends = new ArrayList<Pair<Integer, String>>();
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

	public void addFriend(Pair<Integer, String> newFriend) {
		this.friends.add(newFriend);
	}

	public void addFriends(ArrayList<Pair<Integer, String>> newFriends) {
		if (newFriends == null)
			return;
		if (this.friends.size() == 0)
			this.friends = newFriends;
		else
			for (int i = 0; i < newFriends.size(); i++)
				this.friends.add(newFriends.get(i));

	}

	public boolean hasFriendById(int friendId) {
		return false;
	}

	public boolean hasFriendByUserName(String friendUserName) {
		return false;
	}
}
