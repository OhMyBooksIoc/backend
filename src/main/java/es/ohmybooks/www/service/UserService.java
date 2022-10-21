package es.ohmybooks.www.service;

import es.ohmybooks.www.model.UserModel;

import java.util.Map;
import java.util.Optional;

public interface UserService {

  public abstract Map<String, Object> listUsers();
  public abstract Map<String, Object> addUser(UserModel user);
  public abstract Map<String, Object> updateUser(UserModel user);
  public abstract Optional<UserModel> findById(Long id);
  public abstract Map<String, Object> findByEmail(String email);
  public abstract Map<String, Object> deleteById(Long id);
  //TODO public abstract Set<UserModel> findByRoles(Set<RoleModel> roles);

  public abstract Map<String, Object> login(String email, String password);
  
}