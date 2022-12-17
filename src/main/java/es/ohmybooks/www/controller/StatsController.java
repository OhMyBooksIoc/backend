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

/**
 * Controller de Stats
 * 
 * @author Group3
 * @version 1.0
 */
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

  /**
   * Endpoint que devuelve las estadisticas privadas visibles en el perfil de cada
   * usuario.
   * 
   * @param authorization define el token del usuario logueado.
   * @return un json con Total de libros, Total de libros leidos, Total de paginas
   *         leidas, Total de libros para intercambio.
   */
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

  /**
   * Endpoint que devuelve las estadisticas visibles en la pagina home.
   * 
   * @return un json con Total de libros en la APP, Total de libros leidos en la
   *         APP, Total de libros para intercambio en la APP, Total de usuarios
   *         registrados en la APP, Usuario con más libros leidos, Usuario con más
   *         paginas leidas
   */
  @GetMapping("/public")
  public ResponseEntity<?> getPublicStats() {
    JsonObject json = new JsonObject();
    json.put("totalBooksApp", userBookService.countByStatus(true));
    json.put("totalBooksReaded", userBookService.countByReaddAndStatus(true, true));
    json.put("totalBooksTraded", userBookService.countByTradeAndStatus(true, true));
    json.put("totalRegistredUsers", userService.countByStatus(true));

    int userIdMoreRead = userBookService.getUserIdMoreRead();
    if (userIdMoreRead == 0) {
      json.put("userWithMoreReads", "-");
    } else {
      User userMoreBooksRead = userService.findById(userIdMoreRead).get();
      json.put("userWithMoreReads", userMoreBooksRead.getUserName());
    }

    int userIdMorePagesRead = userBookService.getUserIdMorePageRead();
    if (userIdMorePagesRead == 0) {
      json.put("userWithMorePagesRead", "-");
    } else {
      User userMorePagesRead = userService.findById(userIdMorePagesRead).get();
      json.put("userWithMorePagesRead", userMorePagesRead.getUserName());
    }

    return new ResponseEntity<>(json, HttpStatus.OK);
  }
}