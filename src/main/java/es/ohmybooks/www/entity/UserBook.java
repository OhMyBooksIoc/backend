package es.ohmybooks.www.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.ohmybooks.www.security.entity.User;

/**
 * Clase que define los atributos de cada UserBook
 * 
 * @author Group3
 * @version 1.0
 */
@Entity
@Table(name = "userBook")
@IdClass(UserBookId.class)
public class UserBook {

  @Id
  private int userId;

  @Id
  private int bookId;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Book book;

  @Column(name = "comment", columnDefinition = "varchar(500)")
  private String comment;

  @Column(name = "readd", columnDefinition = "tinyint(1)")
  private boolean readd;

  @Column(name = "hide", columnDefinition = "tinyint(1)")
  private boolean hide;

  @Column(name = "status", columnDefinition = "tinyint(1)")
  private boolean status;

  @Column(name = "trade", columnDefinition = "tinyint(1)")
  private boolean trade;

  @Column(name = "created_at")
  private Date createdAt = new Date();

  /**
   * Metodo constructor por defecto
   */
  public UserBook() {
  }

  public UserBook(User user, Book book) {
    this.user = user;
    this.book = book;
  }

  /**
   * Metodo constructor con 7 parametros
   * 
   * @param userId identificador del usuario en la app
   * @param bookId identificador del libro en la app
   * @param comment comentario sobre el libro
   * @param readd define si el libro esta leido o pendiente
   * @param hide define si el libro esta oculto o visible
   * @param status define si el libro esta activado o desactivado
   * @param createdAt fecha en que se crea la relación entre usuario y libro
   */
  public UserBook(int userId, int bookId, String comment, boolean readd, boolean hide, boolean status, Date createdAt) {
    this.userId = userId;
    this.bookId = bookId;
    this.comment = comment;
    this.readd = readd;
    this.hide = hide;
    this.status = status;
    this.createdAt = createdAt;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @JsonIgnore
  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  @JsonIgnore
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

  public boolean getReadd() {
    return readd;
  }

  public void setReadd(boolean readd) {
    this.readd = readd;
  }

  public boolean getHide() {
    return hide;
  }

  public void setHide(boolean hide) {
    this.hide = hide;
  }

  @JsonIgnore
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean isTrade() {
    return trade;
  }

  public void setTrade(boolean trade) {
    this.trade = trade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    UserBook that = (UserBook) o;
    return Objects.equals(user, that.user) &&
        Objects.equals(book, that.book);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, book);
  }
  
}

