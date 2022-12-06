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

  @GetMapping("/private")
  public ResponseEntity<?> getPrivateStats(@RequestHeader String authorization) {
    String token = authorization.substring(7);
    String userName = jwtProvider.getUserNameFromToken(token);
    User user = userService.getByUserName(userName).get();
    int userId = user.getId();

    JsonObject json = new JsonObject();
    json.put("totalBooksUser", userBookService.countByUserId(userId));
    json.put("totalBooksReadUser", userBookService.countByUserIdAndReadd(userId, true));
    json.put("totalBooksInTradeUser", userBookService.countByUserIdAndTrade(userId, true));
    json.put("totalPagesReadUser", userBookService.getTotalPagesReadFromUser(userId));

    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  @GetMapping("/public")
  public ResponseEntity<?> getPublicStats() {
    JsonObject json = new JsonObject();
    json.put("totalBooksApp", userBookService.countByStatus(true));
    json.put("totalBooksReaded", userBookService.countByReaddAndStatus(true, true));
    json.put("totalBooksTraded", userBookService.countByTradeAndStatus(true, true));
    json.put("totalRegistredUsers", userService.countByStatus(true));

    int userIdMoreRead = userBookService.getUserIdMoreRead();
    if (userIdMoreRead == 0) {
      json.put("userWithMoreReads", "No data available");
    } else {
      User userMoreBooksRead = userService.findById(userIdMoreRead).get();
      json.put("userWithMoreReads", userMoreBooksRead.getUserName());
    }

    int userIdMorePagesRead = userBookService.getUserIdMorePageRead();
    if (userIdMorePagesRead == 0) {
      json.put("userWithMorePagesRead", "No data available");
    } else {
      User userMorePagesRead = userService.findById(userIdMorePagesRead).get();
      json.put("userWithMorePagesRead", userMorePagesRead.getUserName());
    }
    
    return new ResponseEntity<>(json, HttpStatus.OK);
  }
}