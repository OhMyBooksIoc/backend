package es.ohmybooks.www.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "book")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false, columnDefinition = "int(10)")
  private int id;

  @Column(name = "name", nullable = false, columnDefinition = "varchar(155)")
  private String name;

  @Column(name = "author", columnDefinition = "varchar(155)")
  private String author;

  @Column(name = "genre", columnDefinition = "varchar(155)")
  private String genre;

  @Column(name = "saga", columnDefinition = "varchar(155)")
  private String saga;

  @Column(name = "year", columnDefinition = "varchar(4)")
  private int year;

  @Column(name = "pages", columnDefinition = "varchar(10)")
  private int pages;

  @Column(name = "cover", columnDefinition = "varchar(255)")
  private String cover;

	/** 
	 * TODO
	 * añadir campo fecha de creación
	 * @Column(name = "create_data", nullable = false)
	 * @Temporal(TemporalType.DATE)
	 * private Calendar createData;
	 */

  @OneToMany(mappedBy = "book") 
  private List<Collectionn> collectionn = new ArrayList<>();

  /**
   * book constructor without parameters
   */
  public Book() {
  }

  /**
   * book constructor with all parameters
   * 
   * @param name
   * @param author
   * @param genre
   * @param saga
   * @param year
   * @param pages
   * @param cover
   */
  public Book(String name, String author, String genre, String saga, int year, int pages, String cover) {
    this.name = name;
    this.author = author;
    this.genre = genre;
    this.saga = saga;
    this.year = year;
    this.pages = pages;
    this.cover = cover;
  }

  public Book(int id, String name, String author, String genre, String saga, int year, int pages, String cover,
      List<Collectionn> collectionn) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.genre = genre;
    this.saga = saga;
    this.year = year;
    this.pages = pages;
    this.cover = cover;
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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getSaga() {
    return saga;
  }

  public void setSaga(String saga) {
    this.saga = saga;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public List<Collectionn> getCollection() {
    return collectionn;
  }

  public void setCollection(List<Collectionn> collection) {
    this.collectionn = collection;
  }

}
