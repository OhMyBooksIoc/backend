package es.ohmybooks.www.security.dto;

import javax.validation.constraints.*;

/**
 * Clase que define un objeto password
 * 
 * @author Group3
 * @version 1.0
 */
public class Password {

	@NotBlank
	private String oldPassword;
	@NotBlank
	private String newPassword;


	/**
	 * Método constructor por defecto
	 */
	public Password() {
	}

	/**
	 * Método constructor con todos los parámetros
	 * @param oldPassword
	 * @param newPassword
	 */
	public Password(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}