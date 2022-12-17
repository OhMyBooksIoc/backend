package es.ohmybooks.www.security.service;

import es.ohmybooks.www.security.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  /**
   * Metodo que devuelve una lista de todos los usuario de la app.
   * 
   * @return una lista de objetos user.
   */
  public abstract List<User> listUsers();

  /**
   * Metodo que devuelve un usuario segun su id.
   * 
   * @param id identificador del usuario a consultar.
   * @return un objeto user.
   */
  public abstract Optional<User> findById(int id);

  /**
   * Metodo que devuelve un usuario segun su atributo userName.
   * 
   * @param userName define el userName del usuario a consultar.
   * @return un objeto user.
   */
  public abstract Optional<User> getByUserName(String userName);

  /**
   * Metodo que comprueba si un usuario existe segun su id.
   * 
   * @param id identificador del usuario a consultar.
   * @return true o false.
   */
  public Boolean existsById(int id);

  /**
   * Metodo que comprueba si un usuario existe segun su userName.
   * 
   * @param userName define el userName del usuario a consultar.
   * @return true o false.
   */
  public Boolean existsByUserName(String userName);

  /**
   * Metodo qye comprueba si un usuario existe segun su email.
   * 
   * @param email define el email del usuario a consultar.
   * @return true o false.
   */
  public Boolean existsByEmail(String email);

  /**
   * Metodo que elimina un usuario segun su id.
   * 
   * @param id identificador del usuario a eliminar.
   * @return true o false
   */
  public abstract boolean deleteUserById(int id);

  /**
   * Metodo que guarda un usuario en la DB.
   * 
   * @param user
   */
  public void save(User user);

  /**
   * Metodo que devuelve el nuemro de usuarios segun su atributo status.
   * 
   * @param status
   * @return el numero de usuarios activos o desactivados.
   */
  public int countByStatus(boolean status);

}
