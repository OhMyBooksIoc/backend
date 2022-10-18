package es.ohmybooks.www.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "int(10)")
	private Long id;

	@Column(name = "name", nullable = false, columnDefinition = "varchar(30)")
	private String name;

	@Column(name = "last_name", nullable = false, columnDefinition = "varchar(30)")
	private String lastName;

	@Column(name = "email", unique = true, nullable = false, columnDefinition = "varchar(50)")
	private String email;

	@Column(name = "password", nullable = false, columnDefinition = "varchar(255)")
	private String password;

	// options: admin, user, author
	@Column(name = "rol", nullable = false, columnDefinition = "varchar(10) default 'ROLE_USER'")
	private String rol;

	@Column(name = "id_col", nullable = true, columnDefinition = "int(10)")
	private Long idCol;

	public UserModel() {
	}

	public UserModel(Long id, String name, String lastName, String email, String password, String rol, Long idCol) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.idCol = idCol;
	}

	
	/**
	 * @return Long return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return String return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String return the lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastname to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return String return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return String return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return rol return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return Long return the idcol
	 */
	public Long getIdCol() {
		return idCol;
	}

	/**
	 * @param id the idcol to set
	 */
	public void setIdCol(Long idCol) {
		this.idCol = idCol;
	}


	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", last_name=" + lastName + ", email=" + email + ", password="
				+ password + ", rol=" + rol + "id_col=" + idCol + "]";
	}

}