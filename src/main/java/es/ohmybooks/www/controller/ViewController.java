package es.ohmybooks.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller 
public class ViewController {
  
  @GetMapping("/homeView")
  public String homeView() {
    return "home";
  }

  @GetMapping("/usersView")
  public String usersView() {
    return "users";
  }

  @GetMapping("/booksView")
  public String booksView() {
    return "books";
  }

  @GetMapping("/loginView")
  public String loginView() {
    return "login";
  }

  @GetMapping("/signupView")
  public String signupView() {
    return "signup";
  }

  @GetMapping("/welcomeView")
  public String welcomeView() {
    return "welcome";
  }
}