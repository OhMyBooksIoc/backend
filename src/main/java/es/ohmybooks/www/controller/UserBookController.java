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

@RestController // @Controller + @ResponseBody
@RequestMapping("userBook")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UserBookController {

  @Autowired
  UserBookService userBookService;

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  @Autowired
  JwtProvider jwtProvider;

  @GetMapping("/user")
  public ResponseEntity<?> getRegisteredUserBooks(@RequestHeader String authorization) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    int userId = userService.getByUserName(userName).get().getId();
    return new ResponseEntity<>(userBookService.findByUserId(userId), HttpStatus.OK);
  }

  @GetMapping("/user/{userName}")
  public ResponseEntity<?> getBooksByUserName(@PathVariable("userName") String userName) {
    User user = userService.getByUserName(userName).get();
    if (user.isStatus() == false) {
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(userBookService.findByUserIdAndHide(user.getId(), false), HttpStatus.OK);
  }

  @GetMapping("book/{bookId}")
  public ResponseEntity<?> getBookFromAllUsers(@PathVariable("bookId") int bookId) {
    return new ResponseEntity<>(userBookService.findByBookId(bookId), HttpStatus.OK);
  }

  @GetMapping("toTrade")
  public ResponseEntity<?> getBooksToTrade() {
    return new ResponseEntity<>(userBookService.findByTrade(true), HttpStatus.OK);
  }

  @PostMapping("/addBook/{bookId}")
  public ResponseEntity<?> addBookToUser(@RequestHeader String authorization, @PathVariable("bookId") int bookId) {
    // TODO comprobar si el libro ya existe en userbook
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    UserBook userBook = new UserBook();
    userBook.setBookId(bookId);
    userBook.setUserId(userService.getByUserName(userName).get().getId());
    userBook.setHide(false); // default is visible(false)
    userBook.setReadd(false); // default is unread(false)
    userBook.setStatus(true); // default is enable(true)
    userBookService.save(userBook);
    return new ResponseEntity<>(new Message("Added Book to User"), HttpStatus.OK);
  }


  @PutMapping("hide/{idBook}")
  public ResponseEntity<?> hideBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    UserBook userBook = userBookService.findByUserIdAndBookId(userId, idBook);
    if(userBook == null) {
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

  @PutMapping("trade/{idBook}")
  public ResponseEntity<?> tradeBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    UserBook userBook = userBookService.findByUserIdAndBookId(userId, idBook);
    if(userBook == null) {
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

  @PutMapping("/read/{idBook}")
  public ResponseEntity<?> markReadd(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    List<UserBook> userBookList = userBookService.findByUserId(user.getId());
    UserBook selectedUserBook= null;
    for (UserBook userBook : userBookList ){
      if (userBook.getBookId() == idBook){
        selectedUserBook = userBook;
      }
    }

    if (selectedUserBook == null) {
      return new ResponseEntity<>(new Message("This book isn't in your library"), HttpStatus.NOT_FOUND);
    }
    Boolean previousState = selectedUserBook.getReadd();
    selectedUserBook.setReadd(!previousState);
    userBookService.save(selectedUserBook);
    return new ResponseEntity<>(new Message(previousState ? "Book set to unread" : "Book set to read"), HttpStatus.CREATED);
  }

  @DeleteMapping("deleteBook/{idBook}")
  public ResponseEntity<?> deleteBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    int userId = userService.getByUserName(userName).get().getId();
    userBookService.deleteByUserIdAndBookId(userId, idBook);
    Boolean realBook = userBookService.existsById(userId);
    return new ResponseEntity<>(new Message(realBook ? "Book removed from the user profile" : "This book didn't exist in this profile"), HttpStatus.NO_CONTENT);
  }

}

