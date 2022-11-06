package es.ohmybooks.www.security.service;

import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.repository.UserRepository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  public Optional<User> getByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public Boolean existsByUserName(String userName) {
    return userRepository.existsByUserName(userName);
  }

  public Boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public void save(User user) {
    userRepository.save(user);
  }

}
