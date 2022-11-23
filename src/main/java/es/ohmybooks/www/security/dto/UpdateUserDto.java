package es.ohmybooks.www.security.dto;

import javax.validation.constraints.*;

public class UpdateUserDto {

	@NotBlank
	private String name;
	@Email
	private String email;
	private String picture;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
