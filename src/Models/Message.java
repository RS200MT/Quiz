package Models;

public class Message {
	
	private int id;
	private String messageText;
	private int senderId;
	private int recipientId;
	private boolean seen;
	private String receiveTime;
	private int messageId ;
	
	public Message(String text, int senderId, int recipientId,boolean seen,int messageId,String receiveTime) {
		this.messageText = text;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.seen = seen;
		this.receiveTime = receiveTime;
		this.messageId = messageId;
	}
	
	
	
	public int getMessageId(){
		return this.messageId;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getMessageText() {
		return this.messageText;
	}
	
	public int getSenderId() {
		return this.senderId;
	}
	
	public int getRecipientId() {
		return this.recipientId;
	}
	
	public boolean isSeen() {
		return this.seen;
	}
	
	public String getReceiveTime(){
		return this.receiveTime;
	}
	
}
