package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.entity.Collection;
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

  @PutMapping("userName/{userName}/idBook/{idBook}")
  public ResponseEntity<?> addCollectionBook(@PathVariable("userName") String userName, @PathVariable("idBook") int idBook) {
    /*
    int idUser = userService.getByUserName(userName).get().getId();
    if (!userService.existsById(idUser) !bookService.existsById(idBook)) {
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
    } else {
      */
      Collection collection = new Collection();
      collection.setIdBook(idBook);
      collection.setIdUser(userService.getByUserName(userName).get().getId());
      collectionService.save(collection);
      return new ResponseEntity<>(new Message("Added Book to Collection"), HttpStatus.OK);
      /*
    }
    */
  }

}