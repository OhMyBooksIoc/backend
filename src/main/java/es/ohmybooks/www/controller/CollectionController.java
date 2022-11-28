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
import es.ohmybooks.www.entity.Collectionn;
import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.UserService;
import es.ohmybooks.www.service.BookService;
import es.ohmybooks.www.service.CollectionService;

@RestController // @Controller + @ResponseBody
@RequestMapping("collection")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class CollectionController {

  @Autowired
  CollectionService collectionService;

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  @Autowired
  JwtProvider jwtProvider;

  @GetMapping("/user")
  public ResponseEntity<?> getMyCollection(@RequestHeader String authorization) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    int userId = userService.getByUserName(userName).get().getId();
    return new ResponseEntity<>(collectionService.findByUserId(userId), HttpStatus.OK);
  }

  @GetMapping("/user/{userName}")
  public ResponseEntity<?> getCollectionByUser(@RequestHeader String authorization,
      @PathVariable("userName") String userName) {
    User user = userService.getByUserName(userName).get();
    if (user.isStatus() == false) {
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(collectionService.findByUserIdAndHide(user.getId(), false), HttpStatus.OK);
  }

  @GetMapping("book/{bookId}")
  public ResponseEntity<?> getBookAtCollections(@RequestHeader String authorization,
      @PathVariable("bookId") int bookId) {
    return new ResponseEntity<>(collectionService.findByBookId(bookId), HttpStatus.OK);
  }

  @PostMapping("/addBook/{bookId}")
  public ResponseEntity<?> addBookToUser(@RequestHeader String authorization, @PathVariable("bookId") int bookId) {
    // TODO comprobar si el libro ya existe en collection
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    Collectionn collectionn = new Collectionn();
    collectionn.setBookId(bookId);
    collectionn.setUserId(userService.getByUserName(userName).get().getId());
    collectionn.setHide(false); // default is visible(false)
    collectionn.setReadd(false); // default is unread(false)
    collectionn.setStatus(true); // default is enable(true)
    collectionService.save(collectionn);
    return new ResponseEntity<>(new Message("Added Book to Collection"), HttpStatus.OK);
  }

  @DeleteMapping("deleteBook/{bookId}")
  public ResponseEntity<?> deleteBook(@RequestHeader String authorization, @PathVariable("bookId") int bookId) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    int userId = userService.getByUserName(userName).get().getId();
    collectionService.deleteByUserIdAndBookId(userId, bookId);
    return new ResponseEntity<>(new Message("Deleted Collection Book"), HttpStatus.OK);
  }

  @PutMapping("/read/{idBook}")
  public ResponseEntity<?> markReadd(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    List<Collectionn> collectionList = collectionService.findByUserId(user.getId());
    Collectionn selectedCollection = null;
    for (Collectionn collection : collectionList) {
      if (collection.getBookId() == idBook) {
        selectedCollection = collection;
      }
    }

    if (selectedCollection == null) {
      return new ResponseEntity<>(new Message("This book isn't in your collection"), HttpStatus.NOT_FOUND);
    }
    Boolean previousState = selectedCollection.getReadd();
    selectedCollection.setReadd(!previousState);
    collectionService.save(selectedCollection);
    return new ResponseEntity<>(new Message(previousState ? "Book set to unread" : "Book set to read"),
        HttpStatus.CREATED);
  }

  @PutMapping("hide/{idBook}")
  public ResponseEntity<?> hideBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    Collectionn collectionn = collectionService.findByUserIdAndBookId(userId, idBook);
    if (collectionn.getHide() == false) {
      collectionn.setHide(true);
      collectionService.save(collectionn);
      return new ResponseEntity<>(new Message("Hidden Book"), HttpStatus.CREATED);
    } else {
      collectionn.setHide(false);
      collectionService.save(collectionn);
      return new ResponseEntity<>(new Message("Visible Book"), HttpStatus.CREATED);
    }
  }

  @PutMapping("exchange/{idBook}")
  public ResponseEntity<?> exchangeBook(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    Collectionn collectionn = collectionService.findByUserIdAndBookId(userId, idBook);
    if (collectionn.isExchange() == false) {
      collectionn.setExchange(true);
      collectionService.save(collectionn);
      return new ResponseEntity<>(new Message("Book available for exchange"), HttpStatus.CREATED);
    } else {
      collectionn.setExchange(false);
      collectionService.save(collectionn);
      return new ResponseEntity<>(new Message("Book not available for exchange"), HttpStatus.CREATED);
    }
  }

}