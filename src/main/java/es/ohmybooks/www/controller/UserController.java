package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  UserService userService;

  /**
   * endpoint that returns all users in the database
   * @return a json with all users and all their fields
   */
  @GetMapping()
  public ArrayList<UserModel> listUsers(){
    return userService.listUsers();
  }

  /**
   * endpoint that adds the user passed in the parameter to the database
   * @param user
   * @return a json with the added user and all its fields
   */
  @PostMapping()
  public UserModel addUser(@RequestBody UserModel user){
    return this.userService.addUser(user);
  }

  /**
   * endpoint that returns the user with the id equal to the value passed in the parameter
   * @param id
   * @return a json with the searched user and all its fields
   */
  @GetMapping("/{id}")
  public Optional<UserModel> findUserById(@PathVariable("id") Long id) {
    return this.userService.findById(id);
  }
  
  /**
   * endpoint that modifies a user's data with the user's data passed as a parameter
   * @param user
   * @return a json with the modified user and all its fields
   */
  @PutMapping()
  public UserModel updateUser(@RequestBody UserModel user){
    return this.userService.updateUser(user);
  }

  /**
   * endpoint that removes the user with the id equal to the value passed in the parameter
   * @param id
   * @return error message or success of the operation
   */
  @DeleteMapping("/{id}")
  public String deleteUserById(@PathVariable("id") Long id) {
    boolean ok = this.userService.deleteUser(id);
    if(ok) {
      return "Deleted user with id " + id;
    } else {
      return "Couldn't delete user with id " + id;
    }
  }

  /**
   * endpoint that returns the user with the email equal to the value passed in the parameter
   * @param email
   * @return a json with the searched user and all its fields
   */
  @GetMapping("/email/{email}")
  public UserModel findUserByEmail(@PathVariable("email") String email) {
    return this.userService.findByEmail(email);
  }

  /**
   * endpoint that returns the set of users with a status equal to the value passed in the parameter
   * @param id
   * @return a json with the set of matching users and all their fields
   */
  @GetMapping("/query")
  public ArrayList<UserModel> findUserByRol(@RequestParam("rol") String rol) {
    return this.userService.findByRol(rol);
  }
    
}