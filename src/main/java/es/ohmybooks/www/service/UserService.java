package es.ohmybooks.www.service;

import es.ohmybooks.www.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

  public abstract ArrayList<UserModel> listUsers();
  public abstract UserModel addOrUpdateUser(UserModel user);
  public abstract Optional<UserModel> findById(Long id);
  public abstract UserModel findByEmail(String email);
  public abstract boolean deleteUser(Long id);

  public Map<String, String> returnUserData(UserModel user);
  public List<UserModel> returnListUserData(List<UserModel> list);
  
}