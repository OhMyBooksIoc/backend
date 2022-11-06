package es.ohmybooks.www.security.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

public class NewUser {

	@NotBlank
	private String name;
	@NotBlank
	private String userName;
	@Email
	private String email;
	@NotBlank
	private String password;
	// By default it creates a normal user
	// If you want an admin or author, you need to pass this field to roles
	private Set<String> roles = new HashSet<>();

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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
