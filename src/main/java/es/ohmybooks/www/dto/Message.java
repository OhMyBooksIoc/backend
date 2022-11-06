package es.ohmybooks.www.dto;

// class to display messages (front) on the screen on the client
public class Message {

	private String message;

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
