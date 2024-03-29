package es.ohmybooks.www.security.dto;

import javax.validation.constraints.*;

/**
 * Clase que define un objeto Login
 * 
 * @author Group3
 * @version 1.0
 */
public class Login {

	@NotBlank
	private String userName;
	@NotBlank
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
