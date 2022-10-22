package es.ohmybooks.www.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
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

	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
		name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<RoleModel> roles = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
		name = "user_book",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private Set<BookModel> books = new HashSet<>();
	

	public UserModel() {
	}

	public UserModel(Long id, String name, String lastName, String email, Set<RoleModel> roles) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public UserModel(Long id, String name, String lastName, String email, String password, Set<RoleModel> roles, Set<BookModel> books) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.books = books;
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
	 * @return roles return the roles
	 */
	public Set<RoleModel> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	/**
	 * @return Long return the books
	 */
	public Set<BookModel> getBooks() {
		return books;
	}

	/**
	 * @param id the books to set
	 */
	public void setBooks(Set<BookModel> books) {
		this.books = books;
	}

}