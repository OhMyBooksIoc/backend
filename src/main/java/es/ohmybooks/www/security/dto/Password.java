package es.ohmybooks.www.security.dto;

import javax.validation.constraints.*;

public class Password {

	@NotBlank
	private String oldPassword;
	@NotBlank
	private String newPassword;


	public Password() {
	}

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