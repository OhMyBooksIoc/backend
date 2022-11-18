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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ohmybooks.www.dto.Message;
import es.ohmybooks.www.entity.Book;
import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.service.UserService;
import es.ohmybooks.www.service.BookService;

@RestController // @Controller + @ResponseBody
@RequestMapping("collection")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class CollectionController {

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  @GetMapping("idUser/{idUser}")
  public ResponseEntity<?> listBooksByUser(@PathVariable("idUser") int idUser) {
    List<Book> collect = new ArrayList<>();
    if (!userService.existsById(idUser)) {
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

  @PutMapping("idUser/{idUser}/add/{idBook}")
  public ResponseEntity<?> addCollectionBook(@PathVariable("idUser") int idUser, @PathVariable("idBook") int idBook) {
    if (!bookService.existsById(idBook)) {
      return new ResponseEntity<>(new Message("The book doesn't exist"), HttpStatus.NOT_FOUND);
    } else {
      Book book = bookService.findById(idBook).get();
      book.setName(book.getName());
      book.setAuthor(book.getAuthor());
      book.setGenre(book.getGenre());
      book.setSaga(book.getSaga());
      book.setYear(book.getYear());
      book.setPages(book.getPages());
      book.setCover(book.getCover());

      Set<User> users = new HashSet<>();
      users = book.getUsers();
      users.add(userService.findById(idUser).get());

      book.setUsers(users);
      bookService.save(book);
      return new ResponseEntity<>(new Message("Added Book to Collection"), HttpStatus.OK);
    }
  }

}