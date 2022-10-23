package es.ohmybooks.www.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table (name="books")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "int(10)")
	private Long id;

	@Column(name = "name", unique = true, nullable = false, columnDefinition = "varchar(15)")
	private String name;


  public BookModel() { }

  public BookModel(String name) {
    this.name = name;
  }
  

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
