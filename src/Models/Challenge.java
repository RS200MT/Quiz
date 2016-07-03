package Models;

public class Challenge {
	private int id;
	private String sender;
	private String recipient;
	private int quizId;
	private int seen;
	private String receiveTime;
	
	public Challenge(int id, String sender, String recipient, int quizId, int seen, String receiveTime) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.quizId = quizId;
		this.seen = seen;
		this.receiveTime = receiveTime;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public String getSender() {
		return this.sender;
	}
	
	public String getRecipient() {
		return this.recipient;
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
