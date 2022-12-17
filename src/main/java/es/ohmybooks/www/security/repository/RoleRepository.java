package es.ohmybooks.www.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.security.entity.Role;
import es.ohmybooks.www.security.enums.RoleName;

/**
 * Contiene los metodos CRUD relacionados con Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByRoleName(RoleName roleName);

}
