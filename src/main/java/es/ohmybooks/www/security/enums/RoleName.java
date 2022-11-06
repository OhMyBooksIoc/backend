package es.ohmybooks.www.security.enums;

public enum RoleName {
	// Admin has all crud permissions
	// User has all crud permissions except delete
	// Author has all user permissions and access to the authors area
	ROLE_ADMIN, ROLE_USER, ROLE_AUTHOR
}
