package es.ohmybooks.www.security.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import es.ohmybooks.www.entity.Collectionn;

@Entity
@Table(name = "user")
public class User {

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

	@Column(name = "status", nullable = true, columnDefinition = "int(10)")
	private int status;

	/** 
	 * TODO
	 * añadir campo fecha de creación
	 * @Column(name = "create_data", nullable = false)
	 * @Temporal(TemporalType.DATE)
	 * private Calendar createData;
	 */

	// A user can have many roles and a role can belong to multiple users
	// Intermediate table that will have user_id and role_id
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
		name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")  
	private List<Collectionn> collectionn = new ArrayList<>();
	

	public User() {
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String name, String userName, String email, String password) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public User(String name, String userName, String email, String password, String picture, int status) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.status = status;
	}
	
	public User(int id, String name, String userName, String email, String password, String picture, int status,
			Set<Role> roles, List<Collectionn> collectionn) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.status = status;
		this.roles = roles;
		this.collectionn = collectionn;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Collectionn> getCollection() {
		return collectionn;
	}

	public void setCollection(List<Collectionn> collectionn) {
		this.collectionn = collectionn;
	}

}