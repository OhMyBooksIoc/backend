package es.ohmybooks.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.Book;

/**
 * Contiene los metodos CRUD relacionados con Book
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

  List<Book> findByName(String name);

  List<Book> findByAuthor(String author);

  boolean existsByName(String name);

  boolean existsByAuthor(String author);

  Book findByNameAndAuthor(String name, String Author);

}
