package es.ohmybooks.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.model.UserModel;
import es.ohmybooks.www.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;
  
  public ArrayList<UserModel> listUsers() {
    return (ArrayList<UserModel>) userRepository.findAll();
  }

  public UserModel addUser(UserModel user) {
    return userRepository.save(user);
  }

  public Optional<UserModel> findById(Long id) {
    return userRepository.findById(id);
  }

  public UserModel findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public ArrayList<UserModel> findByRol(String rol) {
    return userRepository.findByRol(rol);
  }

  public UserModel updateUser(UserModel user) {
    return userRepository.save(user);
  }

  public boolean deleteUser(Long id) {
    try{
      userRepository.deleteById(id);
      return true;
    }catch (Exception e){
      return false;
    }
  }
    
}