package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.entity.Book;
import es.ohmybooks.www.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  @Override
  public List<Book> listBooks(){
    return bookRepository.findAll();
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Optional<Book> findById(int id) {
    return bookRepository.findById(id);
  }

  @Override
  public List<Book> findByName(String name) {
    return bookRepository.findByName(name);
  }

  @Override
  public List<Book> findByAuthor(String author) {
    return bookRepository.findByAuthor(author);
  }

  @Override
  public Book findByNameAndAuthor(String name, String author) {
    return bookRepository.findByNameAndAuthor(name, author);
  }

  @Override
  public boolean deleteBookById(int id) {
    try {
      bookRepository.deleteById(id);
      return true;
    } catch (Exception e){ 
      return false;
    }
  }

  @Override
  public boolean existsById(int id) {
    return bookRepository.existsById(id);
  }

  @Override
  public boolean existsByName(String book) {
    return bookRepository.existsByName(book);
  }

  @Override
  public boolean existsByAuthor(String author) {
    return bookRepository.existsByAuthor(author);
  }
  
}