package es.ohmybooks.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository <UserModel, Long> {
    public abstract UserModel findByEmail(String Email);
    public abstract UserModel deleteByEmail(String email);
}
