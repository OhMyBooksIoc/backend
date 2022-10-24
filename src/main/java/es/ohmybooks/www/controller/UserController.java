package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
public class UserController {

  @Autowired
  UserService userService;

  /**
   * endpoint that returns all users in the database
   * 
   * @return a json with all users and all their fields (no password)
   */
  @GetMapping()
  public Map<String, Object> listUsers() {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    List<UserModel> list = userService.listUsers();
    resp.put("status", "200 OK");
    resp.put("user", userService.returnListUserData(list));
    return resp;
  }

  /**
   * endpoint that adds the user passed in the parameter to the database
   * 
   * @param user
   * @return a json with the added user and all its fields (no password)
   */
  @PostMapping()
  public Map<String, Object> addUser(@RequestBody UserModel user) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel findUser = userService.findByEmail(user.getEmail());
    if (findUser == null) {
      this.userService.addOrUpdateUser(user);
      resp.put("status", "200 OK");
      resp.put("user", userService.returnUserData(user));
      return resp;
    } else {
      resp.put("status", "401 Unauthorized - Email duplicated");
      return resp;
    }
  }

  /**
   * endpoint that returns the user with the id equal to the value passed in the
   * parameter
   * 
   * @param id
   * @return a json with the searched user and all its fields (no password)
   */
  @GetMapping("/{id}")
  public Map<String, Object> findUserById(@PathVariable("id") Long id) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel user = this.userService.findById(id);
    if (user == null) {
      resp.put("status", "400 ERROR - Id don't exists");
      return resp;
    }
    UserModel userNew = new UserModel(user.getId(), user.getName(), user.getLastName(), user.getEmail(),
        user.getRoles(), user.getBooks());
    resp.put("status", "200 OK");
    resp.put("user", userNew);
    return resp;
  }

  /**
   * endpoint that modifies a user's data with the user's data passed as a
   * parameter
   * 
   * @param user
   * @return a json with the modified user and all its fields (no password)
   */
  @PutMapping()
  public Map<String, Object> updateUser(@RequestBody UserModel user) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel findedUser = userService.findByEmail(user.getEmail());
    UserModel updatedUser = this.userService.addOrUpdateUser(findedUser);
    resp.put("status", "200 OK");
    resp.put("user", userService.returnUserData(updatedUser));
    return resp;
  }

  /**
   * endpoint that removes the user with the id equal to the value passed in the
   * parameter
   * 
   * @param id
   * @return error message or success of the operation
   */
  @DeleteMapping("/{id}")
  public Map<String, Object> deleteUserById(@PathVariable("id") Long id) {
    Map<String, Object> resp = new LinkedHashMap<>();
    boolean ok = this.userService.deleteUser(id);
    if (ok) {
      resp.put("status", "200 OK - Deleted user with id " + id);
      return resp;
    } else {
      resp.put("status", "400 Unauthorized - Couldn't delete user with id " + id);
      return resp;
    }
  }

  /**
   * endpoint that returns the user with the email equal to the value passed in
   * the parameter
   * 
   * @param email
   * @return a json with the searched user and all its fields (no password)
   */
  @GetMapping("/email/{email}")
  public Map<String, Object> findUserByEmail(@PathVariable("email") String email) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel user = this.userService.findByEmail(email);
    if (user == null) {
      resp.put("status", "400 ERROR - Email don't exists");
      return resp;
    }
    resp.put("status", "200 OK");
    resp.put("user", userService.returnUserData(user));
    return resp;
  }

  /**
   * endpoint that returns the user with email and password equal to the value passed in Body
   * 
   * @param dataLoginUser
   * @return a json with the login user and all its fields (no password)
   */
  @PostMapping("/login")
  public Map<String, Object> login(@RequestBody UserModel dataLoginUser) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel user = this.userService.findByEmail(dataLoginUser.getEmail());
    if (user == null) {
      resp.put("status", "401 Unauthorized - Email not found");
      return resp;
    } else {
      if (user.getPassword().equals(dataLoginUser.getPassword())) {
        resp.put("status", "200 OK");
        resp.put("user", userService.returnUserData(user));
        return resp;
      } else {
        resp.put("status", "401 Unauthorized - Password incorrect");
        return resp;
      }
    }
  }

}