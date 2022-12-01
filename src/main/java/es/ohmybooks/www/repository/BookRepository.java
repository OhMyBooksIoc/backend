package es.ohmybooks.www.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

  Optional<Book> findByName(String name);
  
  Optional<Book> findByAuthor(String author);

  boolean existsByName(String name);

  boolean existsByAuthor(String author);

  Book findByNameAndAuthor(String name, String Author);
  
}
