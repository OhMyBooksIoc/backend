package es.ohmybooks.www.entity;

import javax.persistence.*;

import es.ohmybooks.www.security.entity.User;

@Entity
@Table(name = "collection")
@IdClass(CollectionId.class)
public class Collection {

  @Id
  @Column(name = "idUser", unique = true, nullable = false, columnDefinition = "int(10)")
  private int idUser;

  @Id
  @Column(name = "idBook", unique = true, nullable = false, columnDefinition = "int(10)")
  private int idBook;

  @ManyToOne
  @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "idBook", referencedColumnName = "id", insertable = false, updatable = false)
  private Book book;

  @Column(name = "comment", columnDefinition = "varchar(500)")
  private String comment;

  @Column(name = "readd", columnDefinition = "tinyint(1)")
  private int readd;

  @Column(name = "hide", columnDefinition = "tinyint(1)")
  private int hide;

  /**
   * TODO
   * añadir campo fecha de creación
   * 
   * @Column(name = "create_data", nullable = false)
   * @Temporal(TemporalType.DATE)
   *                              private Calendar createData;
   */

  /**
   * collection constructor without parameters
   */
  public Collection() {
  }

  /**
   * 
   * collection constructor with all parameters
   * 
   * @param id
   * @param idUser
   * @param idBook
   * @param user
   * @param book
   * @param comment
   * @param read
   * @param hide
   */
  public Collection(int idUser, int idBook, User user, Book book, String comment, int readd, int hide) {
    this.idUser = idUser;
    this.idBook = idBook;
    this.user = user;
    this.book = book;
    this.comment = comment;
    this.readd = readd;
    this.hide = hide;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public int getIdBook() {
    return idBook;
  }

  public void setIdBook(int idBook) {
    this.idBook = idBook;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getReadd() {
    return readd;
  }

  public void setReadd(int readd) {
    this.readd = readd;
  }

  public int getHide() {
    return hide;
  }

  public void setHide(int hide) {
    this.hide = hide;
  }

}
