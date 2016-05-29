package Questions;

public class PictureQuestion {
	private String question = "";
	private String imgSrc = "";
	private String answer = "";
	
	public PictureQuestion(String question, String url, String answer){
		this.question = question;
		this.imgSrc = url;
		this.answer = answer;
	}
}
