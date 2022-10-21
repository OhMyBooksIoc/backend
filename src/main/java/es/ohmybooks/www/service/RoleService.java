package es.ohmybooks.www.service;

import es.ohmybooks.www.model.RoleModel;

import java.util.ArrayList;
import java.util.Optional;

public interface RoleService {

  public abstract ArrayList<RoleModel> listRoles();
  public abstract RoleModel addOrUpdateRole(RoleModel role);
  public abstract Optional<RoleModel> findById(Long id);
  public abstract boolean deleteById(Long id);
  
}