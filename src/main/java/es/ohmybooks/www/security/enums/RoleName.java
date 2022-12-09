package es.ohmybooks.www.security.enums;

/**
 * Clase que enumera los posibles valores para la clase Role
 * 
 * @author Group3
 * @version 1.0
 */
public enum RoleName {
	// Admin tiene todos los permisos crud
	// User tiene todos los permisos crud excepto borrar i modificar libro
	// Author tiene todos los permisos de user más los accesos a áreas privadas de author
	ROLE_ADMIN, ROLE_USER, ROLE_AUTHOR
}
