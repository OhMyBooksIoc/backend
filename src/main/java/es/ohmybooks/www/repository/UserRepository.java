package es.ohmybooks.www.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository <UserModel, Long> {
    public abstract UserModel findByEmail(String Email);
    public abstract ArrayList<UserModel> findByRol(String rol);
    public abstract UserModel deleteByEmail(String email);
}
