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
	 * Metodo constructor por defecto
	 */
	public UserDto() {
	}

	/**
	 * Metodo constructor con un parametro.
	 * 
	 * @param password define la contraseña del usuario.
	 */
	public UserDto(String password) {
		this.password = password;
	}

	/**
	 * Metodo constructor con todos los parametros.
	 * 
	 * @param name define el nombre del usuario.
	 * @param userName define el userName del usuario.
	 * @param email define el email del usuario.
	 * @param password define la contraseña del usuario.
	 * @param picture define el vinculo a la imagen del usuario.
	 * @param status define el estado del usuario a activado o desactivado.
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
	 * Metodo constructor con 3 parametros
	 * 
	 * @param name define el nombre del usuario.
	 * @param email define el email del usuario.
	 * @param picture define el vinculo a la imagen del usuario.
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
