package es.ohmybooks.www.service;

import es.ohmybooks.www.model.RoleModel;
import es.ohmybooks.www.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public ArrayList<RoleModel> listRoles() {
    return (ArrayList<RoleModel>) roleRepository.findAll(); 
  }

  @Override
  public RoleModel addOrUpdateRole(RoleModel role) {
    return roleRepository.save(role);
  }

  @Override
  public Optional<RoleModel> findById(Long id) {
    return roleRepository.findById(id);
  }

  @Override
  public boolean deleteById(Long id) {
    try{
      roleRepository.deleteById(id);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  
}