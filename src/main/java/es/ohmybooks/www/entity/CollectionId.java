package es.ohmybooks.www.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class CollectionId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "book_id")
  private int bookId;

  public CollectionId() {
  }

  public CollectionId(int userId, int bookId) {
    this.userId = userId;
    this.bookId = bookId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    CollectionId that = (CollectionId) o;
    return Objects.equals(userId, that.userId) &&
        Objects.equals(bookId, that.bookId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, bookId);
  }

}
