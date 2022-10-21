package es.ohmybooks.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.model.RoleModel;
import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Map<String, Object> listUsers() {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    List<UserModel> list = userRepository.findAll();
    resp.put("message", "Status: 200 OK");
    resp.put("users", returnListUserData(list));
    return resp;
  }

  @Override
  public Map<String, Object> addUser(UserModel user) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel findUser = userRepository.findByEmail(user.getEmail());
    if (findUser == null) {
      userRepository.save(user);
      resp.put("message", "Status: 200 OK");
      resp.put("user", returnUserData(user));
      return resp;
    } else {
      resp.put("message", "Status: 401 Unauthorized - Email duplicated");
      return resp;
    }
  }

  @Override
  public Map<String, Object> updateUser(UserModel user) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    userRepository.save(user);
    resp.put("message", "Status: 200 OK");
    resp.put("user", returnUserData(user));
    return resp;
  }

  @Override
  public Optional<UserModel> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Map<String, Object> findByEmail(String email) {
    Map<String, Object> resp = new LinkedHashMap<String, Object>();
    UserModel user = userRepository.findByEmail(email);
    if (user == null) {
      resp.put("message", "Status: 400 ERROR - Email don't exists");
      return resp;
    }
    resp.put("message", "Status: 200 OK");
    resp.put("user", returnUserData(user));
    return resp;
  }

  // TODO
  /*
   * @Override
   * public Set<UserModel> findByRoles(Set<RoleModel> roles) {
   * return userRepository.findByRoles(roles);
   * }
   */

  @Override
  public Map<String, Object> deleteById(Long id) {
    Map<String, Object> resp = new HashMap<>();
    try {
      userRepository.deleteById(id);
      resp.put("message", "Status 200 OK - Deleted user with id " + id);
      return resp;
    } catch (Exception e) {
      resp.put("message", "Status 400 Unauthorized - Couldn't delete user with id " + id);
      return resp;
    }
  }

  @Override
  public Map<String, Object> login(String email, String password) {
    Map<String, Object> resp = new HashMap<>();
    UserModel user = userRepository.findByEmail(email);
    if (user == null) {
      resp.put("message", "Status: 401 Unauthorized - Email not found");
      return resp;
    } else {
      if (user.getPassword().equals(password)) {
        resp.put("message", "Status: 200 OK");
        resp.put("user", returnUserData(user));
        return resp;

      } else {
        resp.put("message", "Status: 401 Unauthorized - Password incorrect");
        return resp;
      }
    }
  }

  /**
   * 
   * @param user
   * @return collection with user data
   */
  public Map<String, String> returnUserData(UserModel user) {
    Map<String, String> userData = new LinkedHashMap<String, String>();
    userData.put("name", user.getName());
    userData.put("lastName", user.getLastName());
    userData.put("email", user.getEmail());

    Set<RoleModel> roles = user.getRoles();
    List<String> listRoles = new ArrayList<>();
    for (RoleModel role : roles) {
      listRoles.add(role.getName());
    }
    userData.put("roles", listRoles.toString());
    return userData;
  }

  public List<UserModel> returnListUserData(List<UserModel> list) {
    List<UserModel> users = new ArrayList<>();
    for (UserModel user : list) {
      UserModel userAdd = new UserModel(user.getId(), user.getName(), user.getLastName(), user.getEmail(),
          user.getRoles());
      users.add(userAdd);
    }
    return users;
  }

}