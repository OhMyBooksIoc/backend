package es.ohmybooks.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.entity.UserBook;
import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.UserService;
import es.ohmybooks.www.service.BookService;
import es.ohmybooks.www.service.UserBookService;

/**
 * Controller de UserBook
 * 
 * @author Group3
 * @version 1.0
 */
@RestController // @Controller + @ResponseBody
@RequestMapping("userBook")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
public class UserBookController {

  @Autowired
  UserBookService userBookService;

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  @Autowired
  JwtProvider jwtProvider;

  /**
   * Endpoint que devuelve los libros del usuario logueado.
   * 
   * @param authorization define el token del usuario logueado.
   * @return una lista de libros con sus atributos.
   */
  @GetMapping("/user")
  public ResponseEntity<?> getRegisteredUserBooks(@RequestHeader String authorization) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    int userId = userService.getByUserName(userName).get().getId();
    return new ResponseEntity<>(userBookService.findByUserId(userId), HttpStatus.OK);
  }

  /**
   * Endpoint que devuelve los libros del usuario pasado por parametro.
   * 
   * @param userName define el userName del usuario de quien se quieren mostrar los libros.
   * @return una lista de libros con sus atributos.
   */
  @GetMapping("/user/{userName}")
  public ResponseEntity<?> getBooksByUserName(@PathVariable("userName") String userName) {
    User user = userService.getByUserName(userName).get();
    if (user.isStatus() == false) {
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(userBookService.findByUserIdAndHide(user.getId(), false), HttpStatus.OK);
  }

  /**
   * Endpoint que devuelve todos los usuarios que tiene un su colección un libro
   * concreto.
   * 
   * @param bookId define el id del libro que se quiere buscar en todos los usuarios.
   * @return una lista de usuarios.
   */
  @GetMapping("book/{bookId}")
  public ResponseEntity<?> getBookFromAllUsers(@PathVariable("bookId") int bookId) {
    return new ResponseEntity<>(userBookService.findByBookId(bookId), HttpStatus.OK);
  }

  /**
   * Endpoint que devuelve todos los libros que los usuarios tienen marcados para
   * intercambio.
   * 
   * @return una lista de libros y sus atributos.
   */
  @GetMapping("toTrade")
  public ResponseEntity<?> getBooksToTrade() {
    return new ResponseEntity<>(userBookService.findByTrade(true), HttpStatus.OK);
  }

  /**
   * Endpoint que añade un libro segun id pasada por parametro a la colección del
   * usuario logueado.
   * 
   * @param authorization define el token del usuario logueado.
   * @param bookId define el id del libro que se quiere añadir al usuario.
   * @return mensaje de confirmacion.
   */
  @PostMapping("/addBook/{bookId}")
  public ResponseEntity<?> addBookToUser(@RequestHeader String authorization, @PathVariable("bookId") int bookId) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    UserBook userBook = new UserBook();
    userBook.setBookId(bookId);
    userBook.setUserId(userService.getByUserName(userName).get().getId());
    userBook.setHide(false); // valor por defecto es false (visible)
    userBook.setReadd(false); // valor por defecto es false (no leido)
    userBook.setStatus(true); // valor por defecto es true (activado)
    userBookService.save(userBook);
    return new ResponseEntity<>(new Message("Added Book to User"), HttpStatus.OK);
  }

  /**
   * Endpoint que cambia entre visible/oculto el libro pasado por id en la url,
   * que pertenece a la colección del usuario logueado.
   * 
   * @param authorization define el token del usuario logueado.
   * @param idBook define el id del libro al que se quiere modificar el atributo hide.
   * @return mensaje de error o confirmacion.
   */
  @PutMapping("hide/{idBook}")
  public ResponseEntity<?> hideBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    UserBook userBook = userBookService.findByUserIdAndBookId(userId, idBook);
    if (userBook == null) {
      return new ResponseEntity<>(new Message("This book isn't in your library"), HttpStatus.NOT_FOUND);
    } else {
      if (userBook.getHide() == false) {
        userBook.setHide(true);
        userBookService.save(userBook);
        return new ResponseEntity<>(new Message("Hidden Book"), HttpStatus.CREATED);
      } else {
        userBook.setHide(false);
        userBookService.save(userBook);
        return new ResponseEntity<>(new Message("Visible Book"), HttpStatus.CREATED);
      }
    }
  }

  /**
   * Endpoint que cambia entre intercambio/no intercambio el libro pasado por id
   * en la url, que pertenece a la colección del usuario logueado.
   * 
   * @param authorization define el token del usuario logueado.
   * @param idBook define el id del libro al que se quiere modificar el atributo trade.
   * @return mensaje de error o confirmacion.
   */
  @PutMapping("trade/{idBook}")
  public ResponseEntity<?> tradeBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    UserBook userBook = userBookService.findByUserIdAndBookId(userId, idBook);
    if (userBook == null) {
      return new ResponseEntity<>(new Message("This book isn't in your library"), HttpStatus.NOT_FOUND);
    } else {
      if (userBook.isTrade() == false) {
        userBook.setTrade(true);
        userBookService.save(userBook);
        return new ResponseEntity<>(new Message("Book available for trade"), HttpStatus.CREATED);
      } else {
        userBook.setTrade(false);
        userBookService.save(userBook);
        return new ResponseEntity<>(new Message("Book not available for trade"), HttpStatus.CREATED);
      }
    }
  }

  /**
   * Endpoint que cambia entre leido/no leido el libro pasado por id en la url,
   * que pertenece a la colección del usuario logueado.
   * 
   * @param authorization define el token del usuario logueado.
   * @param idBook define el id del libro al que se quiere modificar el atributo readd.
   * @return mensaje de error o confirmacion.
   */
  @PutMapping("/read/{idBook}")
  public ResponseEntity<?> markReadd(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    List<UserBook> userBookList = userBookService.findByUserId(user.getId());
    UserBook selectedUserBook = null;
    for (UserBook userBook : userBookList) {
      if (userBook.getBookId() == idBook) {
        selectedUserBook = userBook;
      }
    }

    if (selectedUserBook == null) {
      return new ResponseEntity<>(new Message("This book isn't in your library"), HttpStatus.NOT_FOUND);
    }
    Boolean previousState = selectedUserBook.getReadd();
    selectedUserBook.setReadd(!previousState);
    userBookService.save(selectedUserBook);
    return new ResponseEntity<>(new Message(previousState ? "Book set to unread" : "Book set to read"),
        HttpStatus.CREATED);
  }

  /**
   * Endpoint que elimina un libro segun id pasado por url, de la colección del usuario logueado.
   * 
   * @param authorization define el token del usuario logueado.
   * @param idBook define el id del libro que se quiere eliminar.
   * @return mensaje de error o confirmacion.
   */
  @DeleteMapping("deleteBook/{idBook}")
  public ResponseEntity<?> deleteBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    int userId = userService.getByUserName(userName).get().getId();

    UserBook userBook = userBookService.findByUserIdAndBookId(userId, idBook);

    if (userBook == null) {
      return new ResponseEntity<>(new Message("This book isn't in your library"), HttpStatus.NOT_FOUND);
    } else {
      userBookService.deleteByUserIdAndBookId(userId, idBook);
      return new ResponseEntity<>(new Message("Book removed from the user profile"), HttpStatus.NO_CONTENT);
    }
  }
}
