package es.ohmybooks.www.service;

import es.ohmybooks.www.model.UserModel;

import java.util.ArrayList;
import java.util.Optional;

public interface UserService {

  public abstract ArrayList<UserModel> listUsers();
  public abstract UserModel addUser(UserModel user);
  public abstract Optional<UserModel> findById(Long id);
  public abstract UserModel findByEmail(String email);
  public abstract ArrayList<UserModel> findByRol(String rol);
  public abstract UserModel updateUser(UserModel user);
  public abstract boolean deleteUser(Long id);
  
}