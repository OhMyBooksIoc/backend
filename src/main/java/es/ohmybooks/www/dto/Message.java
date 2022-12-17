package es.ohmybooks.www.dto;

/**
 * Clase para mensaje en display (front) en la parte del cliente
 * 
 * @author Group3
 * @version 1.0
 */
public class Message {

	private String message;

	/**
	 * Metodo constructor por defecto
	 * 
	 * @param message define el mensaje a mostrar.
	 */
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
