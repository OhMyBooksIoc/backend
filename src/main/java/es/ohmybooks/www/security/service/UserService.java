package es.ohmybooks.www.security.service;

import es.ohmybooks.www.security.entity.User;

import java.util.Optional;

public interface UserService {

  public Optional<User> getByUserName(String userName);

  public Boolean existsByUserName(String userName);

  public Boolean existsByEmail(String email);

  public void save(User user);

}
