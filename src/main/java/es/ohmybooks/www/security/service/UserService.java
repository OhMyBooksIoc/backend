package es.ohmybooks.www.security.service;

import es.ohmybooks.www.security.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  public abstract List<User> listUsers();

  public abstract Optional<User> findById(int id);
  
  public abstract Optional<User> getByUserName(String userName);

  public Boolean existsById(int id);

  public Boolean existsByUserName(String userName);

  public Boolean existsByEmail(String email);

  public abstract boolean deleteUserById(int id);

  public void save(User user);

}
