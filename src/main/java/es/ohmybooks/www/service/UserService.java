package es.ohmybooks.www.service;

import es.ohmybooks.www.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserService {

  public abstract ArrayList<UserModel> listUsers();
  public abstract UserModel addOrUpdateUser(UserModel user);
  public abstract UserModel findById(Long id);
  public abstract UserModel findByEmail(String email);
  public abstract boolean deleteUser(Long id);

  public Map<String, Object> returnUserData(UserModel user);
  public List<Map<String, Object>> returnListUserData(List<UserModel> list);
  
}