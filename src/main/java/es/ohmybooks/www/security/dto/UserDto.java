package es.ohmybooks.www.security.dto;

import javax.validation.constraints.*;

/**
 * Clase que define un objeto UserDto
 * 
 * @author Group3
 * @version 1.0
 */
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
	private Boolean status;

	/**
	 * Método constructor por defecto
	 */
	public UserDto() {
	}

	/**
	 * Método constructor con un parámetro
	 * 
	 * @param password
	 */
	public UserDto(String password) {
		this.password = password;
	}

	/**
	 * Método constructor con todos los parámetros
	 * 
	 * @param name
	 * @param userName
	 * @param email
	 * @param password
	 * @param picture
	 * @param status
	 */
	public UserDto(@NotBlank String name, @NotBlank String userName, @Email String email, @NotBlank String password,
			String picture, Boolean status) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.status = status;
	}

	/**
	 * Método constructor con 3 parámetros
	 * 
	 * @param name
	 * @param email
	 * @param picture
	 */
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
