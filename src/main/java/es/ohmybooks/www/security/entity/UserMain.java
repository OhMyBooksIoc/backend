package es.ohmybooks.www.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que genera la seguridad
 * Implementa los privilegios de cada user
 * 
 * @author Group3
 * @version 1.0
 */
public class UserMain implements UserDetails {

	private String name;
	private String userName;
	private String email;
	private String password;
	// Variable que nos da la autorización (no confundir con autenticación)
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Método constructor con 5 parámetros que extiende de GrantedAuthority
	 * 
	 * @param name
	 * @param userName
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public UserMain(String name, String userName, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * Método que assigna los privilegios (autorización)
	 * 
	 * @param user
	 * @return user
	 */
	public static UserMain build(User user) {
		// Convierte el del role del user a GrantedAuthority class
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		return new UserMain(user.getName(), user.getUserName(), user.getEmail(),
				user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
