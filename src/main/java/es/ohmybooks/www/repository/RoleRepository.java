package es.ohmybooks.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.model.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository <RoleModel, Long> {

}
