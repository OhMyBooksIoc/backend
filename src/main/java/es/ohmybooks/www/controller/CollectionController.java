package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.entity.Collectionn;
import es.ohmybooks.www.security.dto.JwtDto;
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

  /*
  @GetMapping("userName/{userName}")
  public ResponseEntity<?> listBooksByUser(@PathVariable("userName") String userName) {
    List<Book> collect = new ArrayList<>();
    if (!userService.existsByUserName(userName)) {
      return new ResponseEntity<>(new Message("The user doesn't exist"), HttpStatus.NOT_FOUND);
    } else {
      List<Book> books = new ArrayList<>();
      books = bookService.listBooks();
      for (int i = 0; i < books.size(); i++) {
        Set<User> users = new HashSet<>();
        users = books.get(i).getUsers();
        for (User id : users) {
          if (id.getId() == idUser) {
            collect.add(books.get(i));
          }
        }
      }
      return new ResponseEntity<>(collect, HttpStatus.OK);
    }
  }
  */

  @PutMapping("/add")
  public ResponseEntity<?> addCollectionnBook(@RequestBody JwtDto jwtDto, @RequestParam("idBook") int idBook) {
    String userName = jwtProvider.getUserNameFromToken(jwtDto.getToken());
    Collectionn collectionn = new Collectionn();
    collectionn.setIdBook(idBook);
    collectionn.setIdUser(userService.getByUserName(userName).get().getId());
    collectionService.save(collectionn);
    return new ResponseEntity<>(new Message("Added Book to Collection"), HttpStatus.OK);
  }

}