package es.ohmybooks.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
   * endpoint that adds the role passed in the parameter to the database
   * @param role
   * @return a json with the added role and all its fields
   */
  @PostMapping()
  public RoleModel addRole(@RequestBody RoleModel role){
    return this.roleService.addOrUpdateRole(role);
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
  
  /**
   * endpoint that modifies a role's data with the role's data passed as a parameter
   * @param role
   * @return a json with the modified role and all its fields
   */
  @PutMapping()
  public RoleModel updateRole(@RequestBody RoleModel role){
    return this.roleService.addOrUpdateRole(role);
  }

  /**
   * endpoint that removes the role with the id equal to the value passed in the parameter
   * @param id
   * @return error message or success of the operation
   */
  @DeleteMapping("/{id}")
  public String deleteRoleById(@PathVariable("id") Long id) {
    boolean ok = this.roleService.deleteById(id);
    if(ok) {
      return "Deleted role with id " + id;
    } else {
      return "Couldn't delete role with id " + id;
    }
  }

}