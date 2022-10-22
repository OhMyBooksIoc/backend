package es.ohmybooks.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.model.RoleModel;
import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.repository.UserRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public ArrayList<UserModel> listUsers() {
    return (ArrayList<UserModel>) userRepository.findAll();
  }

  @Override
  public UserModel addOrUpdateUser(UserModel user) {
    return userRepository.save(user);
  }

  @Override
  public UserModel findById(Long id) {
    return userRepository.findUserById(id);
  }

  @Override
  public UserModel findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public boolean deleteUser(Long id) {
    try{
      userRepository.deleteById(id);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  /**
   * 
   * @param user
   * @return collection with user data
   */
  public Map<String, Object> returnUserData(UserModel user) {
    Map<String, Object> userData = new LinkedHashMap<>();
    userData.put("id", user.getId());
    userData.put("name", user.getName());
    userData.put("lastName", user.getLastName());
    userData.put("email", user.getEmail());

    Set<RoleModel> roles = user.getRoles();
    List<String> listRoles = new ArrayList<>();
    for (RoleModel role : roles) {
      listRoles.add(role.getName());
    }
    userData.put("roles", listRoles);
    userData.put("books", user.getBooks());
    return userData;
  }

  public List<Map<String, Object>> returnListUserData(List<UserModel> list) {
    List<Map<String, Object>> users = new ArrayList<>();
    for (UserModel user : list) {
      users.add(returnUserData(user));
    }
    return users;
  }

}