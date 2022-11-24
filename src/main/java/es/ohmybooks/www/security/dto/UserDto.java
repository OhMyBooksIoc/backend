package es.ohmybooks.www.security.dto;

import javax.validation.constraints.*;

public class UserDto {

	@NotBlank
	private String name;
	@NotBlank
	private String userName;
	@Email
	private String email;
	@NotBlank
	private String password;
	private String picture;
	private String status;

	public UserDto() {
	}

	public UserDto(@NotBlank String name, @NotBlank String userName, @Email String email, @NotBlank String password,
			String picture, String status) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.status = status;
	}

	public UserDto(@NotBlank String name, @Email String email, String picture) {
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
