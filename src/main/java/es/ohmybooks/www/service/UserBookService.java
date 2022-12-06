package es.ohmybooks.www.service;

import java.util.List;

import es.ohmybooks.www.entity.UserBook;

public interface UserBookService {

  public abstract UserBook save(UserBook userBook);

  public abstract List<UserBook> findByUserId(int userId);

  public abstract List<UserBook> findByUserIdAndHide(int userId, boolean hide);

  public abstract List<UserBook> findByBookId(int bookId);

  public abstract boolean deleteRelationUserBook(int userId);

  public abstract boolean existsById(int id);

  public void changeStatusByUserId(int userId);

  public abstract UserBook findByUserIdAndBookId(int userId, int bookId);

  public abstract int countByUserId(int userId);

  public abstract int countByUserIdAndReadd(int userId, boolean readd);

  public abstract int countByUserIdAndTrade(int userId, boolean trade);

  public abstract List<UserBook> findByTrade(boolean trade);

  public abstract List<UserBook> findByReadd(boolean readd);

  public abstract boolean deleteByUserIdAndBookId(int userId, int bookId);

  public abstract int countByStatus(boolean status);


  public abstract int countByReaddAndStatus(boolean readd, boolean status);

  public abstract int countByTradeAndStatus(boolean trade, boolean status);

  public abstract int getUserIdMoreRead();

  public abstract int getUserIdMorePageRead();
}