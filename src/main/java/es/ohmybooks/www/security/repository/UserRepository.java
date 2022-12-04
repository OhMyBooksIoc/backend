package es.ohmybooks.www.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

	boolean existsById(int id);

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);
	
	int countByStatus(boolean status);

}
