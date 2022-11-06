package es.ohmybooks.www.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.security.entity.Role;
import es.ohmybooks.www.security.enums.RoleName;
import es.ohmybooks.www.security.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleRepository roleRepository;

  @Override
  public Optional<Role> getByRoleName(RoleName roleName) {
    return roleRepository.findByRoleName(roleName);
  }
  
  @Override
  public void save(Role role) {
    roleRepository.save(role);
  }

}