package es.ohmybooks.www.security.service;

import es.ohmybooks.www.security.entity.User;
import es.ohmybooks.www.security.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<User> listUsers(){
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(int id) {
    return userRepository.findById(id);
  }
  
  @Override
  public Optional<User> getByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  @Override
  public Boolean existsById(int id) {
    return userRepository.existsById(id);
  }

  @Override
  public Boolean existsByUserName(String userName) {
    return userRepository.existsByUserName(userName);
  }

  @Override
  public Boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public boolean deleteUserById(int id) {
    try {
      userRepository.deleteById(id);
      return true;
    } catch (Exception e){ 
      return false;
    }
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

}
