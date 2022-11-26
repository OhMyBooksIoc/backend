package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    int idUser = userService.getByUserName(userName).get().getId();
    return new ResponseEntity<>(collectionService.findByUserId(idUser), HttpStatus.OK);
  }

  @GetMapping("/user/{userName}")
  public ResponseEntity<?> getCollectionByUser(@RequestHeader String authorization, @PathVariable("userName") String userName) {
    User user = userService.getByUserName(userName).get();
    if (user.isStatus()==false) {
			return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(collectionService.findByUserId(user.getId()), HttpStatus.OK);
  }

  @GetMapping("book/{idBook}")
  public ResponseEntity<?> getBookAtCollections(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    return new ResponseEntity<>(collectionService.findByBookId(idBook), HttpStatus.OK);
  }

  @PostMapping("/addBook/{idBook}")
  public ResponseEntity<?> addBookToUser(@RequestHeader String authorization, @PathVariable("idBook") int idBook) {
    // TODO comprobar si el libro ya existe en collection
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    Collectionn collectionn = new Collectionn();
    collectionn.setBookId(idBook);
    collectionn.setUserId(userService.getByUserName(userName).get().getId());
    collectionn.setHide(false); //default is visible(false)
    collectionn.setReadd(false); //default is unread(false)
    collectionn.setStatus(true); //default is enable(true)
    collectionService.save(collectionn);
    return new ResponseEntity<>(new Message("Added Book to Collection"), HttpStatus.OK);
  }

}