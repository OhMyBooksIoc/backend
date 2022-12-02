package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

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

  //Estadístiques de perfil

  /*@GetMapping("/totalBooks")
  public ResponseEntity<?> getBooks(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/readBooks")
  public ResponseEntity<?> getReadBooks(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/readPages")
  public ResponseEntity<?> getReadPages(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/booksToTrade")
  public ResponseEntity<?> getAvailableTrade(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/lastBook")
  public ResponseEntity<?> lastAddedBook(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  //Estadístiques home(Llibres)

  @GetMapping("/mostRead")
  public ResponseEntity<?> mostReadedBook(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/mostPurchased")
  public ResponseEntity<?> mostPurchasedBook(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/popularSaga")
  public ResponseEntity<?> popularSaga(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/morePages")
  public ResponseEntity<?> morePages(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/popularGenre")
  public ResponseEntity<?> popularGenre(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/lastBook")
  public ResponseEntity<?> lastBookAdded(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/booksToTrade")
  public ResponseEntity<?> bookToTrade(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/booksAdded")
  public ResponseEntity<?> kastWeekBooks(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  //Estadístiques home (usuari)

  @GetMapping("/userReadBooks")
  public ResponseEntity<?> userMostBooks(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/userReadPages")
  public ResponseEntity<?> userMostPages(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/usersRegistered")
  public ResponseEntity<?> weekUserReg(@RequestHeader String authorization) {
    return new ResponseEntity<>(HttpStatus.OK);
  }*/

}