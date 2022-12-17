package es.ohmybooks.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.UserBook;

/**
 * Contiene los metodos CRUD relacionados con UserBook
 */
@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Integer> {

  List<UserBook> findByUserId(int userId);

  List<UserBook> findByUserIdAndHide(int userId, boolean hide);

  List<UserBook> findByUserIdAndReadd(int userId, boolean readd);

  List<UserBook> findByBookId(int bookId);

  boolean deleteByUserId(int userId);

  UserBook findByUserIdAndBookId(int userId, int bookId);

  int countByUserId(int userId);

  int countByUserIdAndReadd(int userId, boolean readd);

  int countByUserIdAndTrade(int userId, boolean trade);

  List<UserBook> findByTrade(boolean trade);

  List<UserBook> findByReadd(boolean readd);

  boolean deleteByUserIdAndBookId(int userId, int bookId);

  int countByStatus(boolean status);

  int countByReaddAndStatus(boolean readd, boolean status);

  int countByTradeAndStatus(boolean trade, boolean status);

}
