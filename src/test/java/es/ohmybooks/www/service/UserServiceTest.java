package es.ohmybooks.www.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.repository.UserRepository;

public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  private UserModel user;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    user = new UserModel();
    user.setName("Alba");
    user.setLastName("Caballero");
    user.setEmail("alba@gmail.com");
    user.setPassword("alba");
    user.setRol("user");
  }

  @Test
  public void testListUsers() {
    //ERROR when(userRepository.findAll()).thenReturn(Arrays.asList(user));
    

  }

  @Test
  public void testAddUser() {
    when(userRepository.save(any())).thenReturn(user);
    assertNotNull(userService.addUser(new UserModel()));
  }

  @Test
  public void testDeleteUser() {

  }

  @Test
  public void testFindByEmail() {
    String email = "alba@gmail.com";

    when(userRepository.findByEmail(email)).thenReturn(user);

    assertNotNull(userService.findByEmail(email));
    UserModel userResult = userService.findByEmail(email);
    assertEquals(email, userResult.getEmail());
    assertEquals(new String("Alba"), userResult.getName());
    //ERROR verify(userRepository, times(1)).findByEmail(email);
  }

  @Test
  public void testFindById() {

  }

  @Test
  public void testFindByStatus() {

  }

  @Test
  public void testUpdateUser() {

  }
}
