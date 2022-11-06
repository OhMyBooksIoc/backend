package es.ohmybooks.www.security.service;

import java.util.Optional;

import es.ohmybooks.www.security.entity.Role;
import es.ohmybooks.www.security.enums.RoleName;

public interface RoleService {

  public Optional<Role> getByRoleName(RoleName roleName);

  public void save(Role role);

}