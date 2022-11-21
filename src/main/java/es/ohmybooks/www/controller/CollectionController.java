package es.ohmybooks.www.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.entity.Book;
import es.ohmybooks.www.entity.Collectionn;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.UserService;
import es.ohmybooks.www.service.BookService;
import es.ohmybooks.www.service.CollectionService;

@RestController // @Controller + @ResponseBody
@RequestMapping("collection")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class CollectionController {

  @Autowired
  CollectionService collectionService;

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  @Autowired
  JwtProvider jwtProvider;

  
  @GetMapping("list")
  public ResponseEntity<?> listBooksByUser(@RequestHeader String token) {
    String userName = jwtProvider.getUserNameFromToken(token);
    List<Book> collect = new ArrayList<>();
    if (!userService.existsByUserName(userName)) {
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    } 
    else {
      /*
      List<Book> books = new ArrayList<>();
      books = bookService.listBooks();
      for (int i = 0; i < books.size(); i++) {
        Set<Collectionn> collectionn = new HashSet<>();
        collectionn = books.get(i).getCollection();
        for (Collectionn item : collectionn) {
          if (item.getIdUser() == userService.getByUserName(userName).get().getId()) {
            collect.add(books.get(i));
          }
        }
      }
      */
      return new ResponseEntity<>(collect, HttpStatus.OK);
    }
  }

  @PutMapping("/add")
  public ResponseEntity<?> addCollectionnBook(@RequestHeader String token, @RequestParam("idBook") int idBook) {
    String userName = jwtProvider.getUserNameFromToken(token);
    Collectionn collectionn = new Collectionn();
    collectionn.setIdBook(idBook);
    collectionn.setIdUser(userService.getByUserName(userName).get().getId());
    collectionService.save(collectionn);
    return new ResponseEntity<>(new Message("Added Book to Collection"), HttpStatus.OK);
  }

}