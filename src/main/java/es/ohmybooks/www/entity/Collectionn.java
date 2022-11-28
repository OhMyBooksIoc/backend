package es.ohmybooks.www.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.ohmybooks.www.security.entity.User;

@Entity
@Table(name = "collection")
@IdClass(CollectionId.class)
public class Collectionn {

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

  @Column(name = "exchange", columnDefinition = "tinyint(1)")
  private boolean exchange;

  @Column(name = "created_at")
  private Date createdAt = new Date();

  
  public Collectionn() {
  }

  public Collectionn(User user, Book book) {
    this.user = user;
    this.book = book;
  }

  public Collectionn(int userId, int bookId, String comment, boolean readd, boolean hide, boolean status, Date createdAt) {
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

  public boolean isExchange() {
    return exchange;
  }

  public void setExchange(boolean exchange) {
    this.exchange = exchange;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    Collectionn that = (Collectionn) o;
    return Objects.equals(user, that.user) &&
        Objects.equals(book, that.book);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, book);
  }
  
}

