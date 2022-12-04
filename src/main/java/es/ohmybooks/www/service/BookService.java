package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import es.ohmybooks.www.entity.Book;

public interface BookService {

  public abstract List<Book> listBooks();

  public abstract Book save(Book book);

  public abstract Optional<Book> findById(int id);

  public abstract Optional<Book> findByName(String name);
  
  public abstract Optional<Book> findByAuthor(String author);

  public abstract boolean deleteBookById(int id);

  public boolean existsById(int id);

  public boolean existsByName(String name);

  public boolean existsByAuthor(String author);

  public abstract Book findByNameAndAuthor(String name, String Author);

  public abstract long count();

}