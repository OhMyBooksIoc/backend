package es.ohmybooks.www.service;

import java.util.List;

import es.ohmybooks.www.entity.UserBook;

public interface UserBookService {

  public abstract UserBook save(UserBook userBook);

  public abstract List<UserBook> findByUserId(int userId);

  public abstract List<UserBook> findByUserIdAndHide(int userId, boolean hide);

  public abstract List<UserBook> findByBookId(int bookId);

  public abstract boolean deleteRelationUserBook(int userId);

  public boolean existsById(int id);

  public void changeStatusByUserId(int userId);

  public UserBook findByUserIdAndBookId(int userId, int bookId);


}