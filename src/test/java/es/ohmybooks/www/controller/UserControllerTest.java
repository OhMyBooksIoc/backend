package es.ohmybooks.www.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.service.UserService;
import java.util.Optional;

@SpringBootTest
@Sql("/init.sql")
public class UserControllerTest {

  @Autowired
  private UserService userService;

  @Test
  public void testAddUser() {
    UserModel user = new UserModel();
    user.setName("Alba");
    user.setLastName("Caballero");
    user.setEmail("alba@gmail.com");
    user.setPassword("alba");
    user.setRol("user");
    user.setIdCol(null);
    UserModel createdUser = userService.addUser(user);
    assertNotNull(createdUser);
    assertEquals(user.getName(), createdUser);
    assertNotEquals(0, createdUser.getId());

    List<UserModel> users = userService.listUsers();
    assertEquals(6, users.size());
  }

  @Test
  public void testDeleteUserById() {

  }

  @Test
  public void testFindUserByEmail() {
    String email = "andres@gmail.com";
    String wrongemail = "wrongemail@gmail.com";
    UserModel user = userService.findByEmail(email);
    assertNotNull(user);
    user = userService.findByEmail(wrongemail);
    assertNull(user);
  }

  @Test
  public void testFindUserById() {
    Long id = 3L;
    Long wrongid = 0L;
    Optional<UserModel> user = userService.findById(id);
    assertNotNull(user);
    user = userService.findById(wrongid);
    assertNull(user);
  }

  @Test
  public void testFindUserByStatus() {

  }

  @Test
  public void testListUsers() {

  }

  @Test
  public void testUpdateUser() {

  }
}
