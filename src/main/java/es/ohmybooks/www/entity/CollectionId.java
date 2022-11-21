package es.ohmybooks.www.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class CollectionId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "idUser")
  private int idUser;

  @Column(name = "idBook")
  private int idBook;
 
  
  public CollectionId() {
  }

  public CollectionId(int idUser, int idBook) {
    this.idUser = idUser;
    this.idBook = idBook;
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
   
}
