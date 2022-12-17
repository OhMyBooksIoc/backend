package es.ohmybooks.www.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Clase que define un objeto JwtDto
 * 
 * @author Group3
 * @version 1.0
 */
public class JwtDto {

	private String token;
	private String bearer = "Bearer";
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Metodo constructor con 3 parametros
	 * 
	 * @param token define el token del usuario.
	 * @param userName define el userName del usuario.
	 * @param authorities define la autorizacion (roles) del usuario.
	 */
	public JwtDto(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.userName = userName;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
