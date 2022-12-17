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

/**
 * Controller de Books
 * 
 * @author Group3
 * @version 1.0
 */
@RestController // @Controller + @ResponseBody
@RequestMapping("book")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class BookController {

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  /**
   * Endpoint que devuelve todos los libros de la app.
   * 
   * @return un json con todos los libros y sus atributos.
   */
  @GetMapping("list")
  public ResponseEntity<?> listBooks() {
    return new ResponseEntity<>(bookService.listBooks(), HttpStatus.OK);
  }

  /**
   * Endpoint que devuelve el libro con el id igual al valor pasado por parametro.
   * 
   * @param id define el id del libro que se quiere obtener.
   * @return un mensaje de error o un json con el libro buscado y todos sus atributos.
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
   * Endpoint que devuelve el libro con el nombre igual al valor pasado por parametro.
   * 
   * @param name define el titulo del libro que se quiere obtener.
   * @return un mensaje de error o un json con el libro buscado y todos sus atributos.
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
   * Endpoint que comprueba si el libro pasado por parametro existe en la app y, si no, lo añade.
   * 
   * @param bookDto define el objeto bookDto que contiene los atributos para crear un nuevo libro.
   * @return si existe el libro devuelve un mensaje de error y, si no existe, devuelve el libro añadido 
   * con todos sus atributos.
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
      return new ResponseEntity<>(bookService.findByNameAndAuthor(book.getName(), book.getAuthor()), HttpStatus.CREATED);
    }
  }

  /**
   * Endpoint que modifica el valor de los traibutos de un libro.
   * Autorizacion solo para Role_Admin.
   * 
   * @param idBook define el id del libro que se va a modificar.
   * @param bookDto define el objeto bookDto que contiene los atributos para modificar un libro existente.
   * @return un mensaje de error o de confirmacion.
   */
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("update/{idBook}")
  public ResponseEntity<?> updateBook(@PathVariable("idBook") int idBook, @RequestBody BookDto bookDto) {
    if (!bookService.existsById(idBook)) {
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
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
   * Endpoint que elimina el libro con id igual al valor pasado por parametro.
   * 
   * @param idBook define el id del libro que se quiere eliminar.
   * @return un mensaje de error o de confirmacion.
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