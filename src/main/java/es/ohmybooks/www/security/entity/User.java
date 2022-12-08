package es.ohmybooks.www.security.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.ohmybooks.www.entity.UserBook;

/**
 * Clase que define los atributos de cada user
 * 
 * @author Group3
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "int(10)")
	private int id;

	@Column(name = "name", nullable = false, columnDefinition = "varchar(30)")
	private String name;

	@Column(name = "user_name", unique = true, nullable = false, columnDefinition = "varchar(30)")
	private String userName;

	@Column(name = "email", unique = true, nullable = false, columnDefinition = "varchar(50)")
	private String email;

	@Column(name = "password", nullable = false, columnDefinition = "varchar(255)")
	private String password;

	@Column(name = "picture", nullable = true, columnDefinition = "varchar(255)")
	private String picture;

	@Column(name = "status", columnDefinition = "tinyint(1)")
	private boolean status;

	@Column(name = "disable_at")
	private Date disableAt;

	@Column(name = "created_at")
	private Date createdAt = new Date();

	// Un user puede tener muchos roles y un role puede pertenecer a multiples user
	// Tabla intermedia que tendrá user_id and role_id
	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserBook> books = new HashSet<>();

	/**
	 * Método constructor por defecto
	 */
	public User() {
	}

	/**
	 * Método constructor con 2 parámetros
	 * 
	 * @param email
	 * @param password
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Método constructor con 4 parámetros
	 * 
	 * @param name
	 * @param userName
	 * @param email
	 * @param password
	 */
	public User(String name, String userName, String email, String password) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Método constructor con 6 parámetros
	 * 
	 * @param name
	 * @param userName
	 * @param email
	 * @param password
	 * @param picture
	 * @param status
	 */
	public User(String name, String userName, String email, String password, String picture, boolean status) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.status = status;
	}

	/**
	 * Método constructor con todos los parámetros
	 * 
	 * @param id
	 * @param name
	 * @param userName
	 * @param email
	 * @param password
	 * @param picture
	 * @param status
	 * @param roles
	 * @param books
	 */
	public User(int id, String name, String userName, String email, String password, String picture, boolean status,
			Set<Role> roles, Set<UserBook> books) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.status = status;
		this.roles = roles;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDisableAt() {
		return disableAt;
	}

	public void setDisableAt(Date disableAt) {
		this.disableAt = disableAt;
	}

	@JsonIgnore
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonIgnore
	public Set<UserBook> getBooks() {
		return books;
	}

	public void setBooks(Set<UserBook> books) {
		this.books = books;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;
		return Objects.equals(userName, user.userName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}