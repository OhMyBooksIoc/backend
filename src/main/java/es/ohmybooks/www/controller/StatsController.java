package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cliftonlabs.json_simple.JsonObject;

import org.springframework.web.bind.annotation.RequestMethod;

import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.jwt.JwtProvider;
import es.ohmybooks.www.security.service.UserService;
import es.ohmybooks.www.service.BookService;
import es.ohmybooks.www.service.UserBookService;

@RestController
@RequestMapping("stats")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public class StatsController {
  @Autowired
  UserBookService userBookService;

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  @Autowired
  JwtProvider jwtProvider;

  //#region Profile Stats

  @GetMapping("/totalBooksFromUser")
  public ResponseEntity<?> getTotalBooksFromUser( @RequestHeader String authorization) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    JsonObject json = new JsonObject();
    json.put("Message", "Total books from " + user.getName());
    json.put("Result", userBookService.countByUserId(userId));
    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  @GetMapping("/totalBooksReadFromUser")
  public ResponseEntity<?> getTotalBooksReadFromUser(@RequestHeader String authorization) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();
    JsonObject json = new JsonObject();
    json.put("Message", "Total books readed from " + user.getName());
    json.put("Result", userBookService.countByUserIdAndReadd(userId, true));
    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  /*@GetMapping("/totalPagesReadFromUser")
  public ResponseEntity<?> getTotalPagesReadFromUser(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/totalBooksToTradeForTheUser")
  public ResponseEntity<?> getUserAvailableTrade(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }*/

  //#endregion

  //#region Home - Book Stats
  
  @GetMapping("/public")
  public ResponseEntity<?> getTotalBooksInTheApp() {
    JsonObject json = new JsonObject();
    json.put("totalBooksApp", userBookService.countByStatus(true));
    json.put("totalBooksReaded", userBookService.countByReaddAndStatus(true, true));
    json.put("totalBooksTraded", userBookService.countByTradeAndStatus(true, true));
    json.put("totalRegistredUsers", userService.countByStatus(true));
    int userId = userBookService.getUserIdMoreRead();
    User user = userService.findById(userId).get();
    json.put("userWithMoreReads", user.getUserName()); 
    json.put("userWithMorePagesRead", "Lorem Ipsum"); // TODO: Afegir usuari amb més pagines llegides (Sum totes les pagines del llbires llegits)
    return new ResponseEntity<>(json, HttpStatus.OK);
  }
  
/* 
  @GetMapping("/userWithMostPagesRead")
  public ResponseEntity<?> getUserWithMostPagesRead() {
    return new ResponseEntity<>(HttpStatus.OK);
  }
*/
}