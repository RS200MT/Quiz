package Models;

public class Challenge {
	private int id;
	private int senderId;
	private int recipientId;
	private int quizId;
	private int seen;
	private String receiveTime;
	
	public Challenge(int id, int senderId, int recipientId, int quizId, int seen, String receiveTime) {
		this.id = id;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.quizId = quizId;
		this.seen = seen;
		this.receiveTime = receiveTime;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public int getSenderId() {
		return this.senderId;
	}
	
	public int getRecipientId() {
		return this.recipientId;
	}
	
	public int getQuizId() {
		return this.quizId;
	}
	
	public boolean isSeen() {
		boolean seen = this.seen==1 ? true : false;
		return seen;
	}
	
	public String getReceiveTime() {
		return this.receiveTime;
	}
}
