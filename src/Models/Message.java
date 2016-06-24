package Models;

public class Message {
	public static enum MessageType{
		Null, Challenge, Note
	}
	
	private MessageType type;
	private String messageText;
	private int senderId;
	private int recipientId;
	private boolean seen;
	private String receiveTime;
	
	public Message( String text, int senderId, int recipientId,String receiveTime) {
		this.type = type;
		this.messageText = text;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.seen = false;
		this.receiveTime = receiveTime;
	}
	
	public MessageType getType() {
		return this.type;
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
