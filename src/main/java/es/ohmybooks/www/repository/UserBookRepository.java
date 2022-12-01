package es.ohmybooks.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.UserBook;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Integer> {

  List<UserBook> findByUserId(int userId);

  List<UserBook> findByUserIdAndHide(int userId, boolean hide);
  
  List<UserBook> findByBookId(int bookId);

  boolean deleteByUserId(int userId);

  boolean existsByUserId(int userId);

  boolean existsByBookId(int bookId);

  UserBook findByUserIdAndBookId(int userId, int bookId);

}
