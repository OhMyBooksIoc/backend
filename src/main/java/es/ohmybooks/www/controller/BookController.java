package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ohmybooks.www.dto.BookDto;
import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.entity.Book;
import es.ohmybooks.www.security.service.UserService;
import es.ohmybooks.www.service.BookService;

@RestController // @Controller + @ResponseBody
@RequestMapping("book")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class BookController {

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  /**
   * endpoint that returns all books in the database
   * 
   * @return a json with all books and all their fields
   */
  @GetMapping("list")
  public ResponseEntity<?> listBooks() {
    return new ResponseEntity<>(bookService.listBooks(), HttpStatus.OK);
  }

  /**
   * endpoint that returns the book with the id equal to the value passed in the
   * parameter
   * 
   * @param id
   * @return a error message or a json with the searched book and all its fields
   */
  @GetMapping("id/{id}")
  public ResponseEntity<?> findBookById(@PathVariable("id") int id) {
    if (!bookService.existsById(id)) {
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }
  }

  /**
   * endpoint that returns the book with the name equal to the value passed in
   * the parameter
   * 
   * @param name
   * @return a error message or a json with the searched book and all its fields
   */
  @GetMapping("name/{name}")
  public ResponseEntity<?> bookByName(@PathVariable("name") String name) {
    if (!bookService.existsByName(name)) {
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(bookService.findByName(name), HttpStatus.OK);
    }
  }

  /**
   * endpoint that checks if the book passed by parameter exists in the database,
   * if it exists it returns an error message and if it doesn't add it
   * 
   * @param bookDto
   * @return a confirmation or error message
   */
  @PostMapping("add")
  public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
    if (!StringUtils.hasLength(bookDto.getName())) {
      return new ResponseEntity<>(new Message("Name is required"), HttpStatus.BAD_REQUEST);
    } else if (bookService.existsByName(bookDto.getName()) && bookService.existsByAuthor(bookDto.getAuthor())) {
      return new ResponseEntity<>(new Message("There is already a book with that name and author"),
          HttpStatus.BAD_REQUEST);
    } else {
      Book book = new Book(bookDto.getName(), bookDto.getAuthor(), bookDto.getGenre(), bookDto.getSaga(),
          bookDto.getYear(), bookDto.getPages(), bookDto.getCover());
      bookService.save(book);
      return new ResponseEntity<>(bookService.findByNameAndAuthor(book.getName(), book.getAuthor()), HttpStatus.OK);
    }
  }

  /**
   * endpoint that checks if the book passed by parameter exists in the database,
   * if it exists it returns an error message and if it doesn't add it
   * 
   * @param bookDto
   * @return a confirmation or error message
   */
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("update/{idBook}")
  public ResponseEntity<?> updateBook(@PathVariable("idBook") int idBook, @RequestBody BookDto bookDto) {
    if (!bookService.existsById(idBook)) {
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
    /** TODO actualizar comprobando nombre y autor pero permitiendo modificar el resto si se estos campos no se modifican
    } else if (bookService.existsByName(bookDto.getName()) && bookService.existsByAuthor(bookDto.getAuthor())) {
      return new ResponseEntity<>(new Message("There is already a book with that name and author"),
          HttpStatus.BAD_REQUEST);
    */
    } else if (!StringUtils.hasLength(bookDto.getName())) {
      return new ResponseEntity<>(new Message("Name is required"), HttpStatus.BAD_REQUEST);
    } else {
      Book book = bookService.findById(idBook).get();
      book.setName(bookDto.getName());
      book.setAuthor(bookDto.getAuthor());
      book.setGenre(bookDto.getGenre());
      book.setSaga(bookDto.getSaga());
      book.setYear(bookDto.getYear());
      book.setPages(bookDto.getPages());
      book.setCover(bookDto.getCover());
      bookService.save(book);
      return new ResponseEntity<>(new Message("Updated Book"), HttpStatus.OK);
    }
  }

  /**
   * endpoint that removes the book with the id equal to the value passed in the
   * parameter
   * 
   * @param idBook
   * @return a confirmation or error message
   */
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("delete/{idBook}")
  public ResponseEntity<?> deleteBook(@PathVariable("idBook") int idBook) {
    if (!bookService.existsById(idBook))
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
    bookService.deleteBookById(idBook);
    return new ResponseEntity<>(new Message("Deleted Book"), HttpStatus.OK);
  }

}