package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

import es.ohmybooks.www.model.RoleModel;
import es.ohmybooks.www.service.RoleService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/role")
public class RoleController {
  
  @Autowired
  RoleService roleService;

  /**
   * endpoint that returns all roles in the database
   * @return a json with all roless and all their fields
   */
  @GetMapping()
  public ArrayList<RoleModel> listRoles(){
    return roleService.listRoles();
  }

  /**
   * endpoint that returns the role with the id equal to the value passed in the parameter
   * @param id
   * @return a json with the searched role and all its fields
   */
  @GetMapping("/{id}")
  public Optional<RoleModel> findRoleById(@PathVariable("id") Long id) {
    return this.roleService.findById(id);
  }

}