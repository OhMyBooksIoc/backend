package es.ohmybooks.www.security.entity;

import javax.persistence.*;

import es.ohmybooks.www.security.enums.RoleName;

@Entity
@Table (name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "int(10)")
	private int id;

	@Enumerated(EnumType.STRING)
  @Column(name = "name", unique = true, nullable = false, columnDefinition = "varchar(15)")
	private RoleName roleName;


  public Role() { }

  public Role(RoleName roleName) {
    this.roleName = roleName;
  }
  

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public RoleName getRoleName() {
    return roleName;
  }

  public void setRoleName(RoleName roleName) {
    this.roleName = roleName;
  }

}
