package es.ohmybooks.www.service;

import java.util.List;

import es.ohmybooks.www.entity.UserBook;

public interface UserBookService {

  public abstract UserBook save(UserBook userBook);

  /**
   * Metodo que devuelve la lista de libros que estan asociados a un usuario.
   * 
   * @param userId define el id del usuario del cual se quiere obtener la lista de
   *               libros.
   * @return una lista de libros
   */
  public abstract List<UserBook> findByUserId(int userId);

  public abstract List<UserBook> findByUserIdAndHide(int userId, boolean hide);

  /**
   * Metodo que devuelve una lista de libros asociados a un usuario y segun su
   * atributo readd.
   * 
   * @param userId define el id del usuario del cual se quiere obtener la lista de
   *               libros.
   * @param hide   define si un libro esta leido o pendiente.
   * @return una lista de libros.
   */
  public abstract List<UserBook> findByUserIdAndReadd(int userId, boolean readd);

  /**
   * Metodo que devuelve una lista de usuarios que tienen en su coleccion un libro
   * concreto.
   * 
   * @param bookId define el id del libro del cual se quiere obtener la lista de
   *               usuarios.
   * @return una lista de usuarios.
   */
  public abstract List<UserBook> findByBookId(int bookId);

  public abstract boolean deleteRelationUserBook(int userId);

  public abstract boolean existsById(int id);

  public void changeStatusByUserId(int userId);

  /**
   * Metodo que devuelve un libro asociado a un usuario concreto.
   * 
   * @param userId define el id del usuario que se quiere consultar.
   * @param bookId define el id del libro que se quiere buscar.
   * @return un usuario y libro
   */
  public abstract UserBook findByUserIdAndBookId(int userId, int bookId);

  /**
   * Metodo que devuelve el numero de libros que tiene un usuario.
   * 
   * @param userId define el id del usuario del cual se quieren saber el numero de
   *               libros.
   * @return el numero de libros asociados a un usuario.
   */
  public abstract int countByUserId(int userId);

  /**
   * Metodo que devuelve el numero de libros que ha leido o tiene pendientes un
   * usuario.
   * 
   * @param userId define el id del usuario del cual se quieren saber el numero de
   *               libros.
   * @param readd  define si un libro esta pendiente o leido.
   * @return el numero de libros que ha leido o tiene pendientes un usuario.
   */
  public abstract int countByUserIdAndReadd(int userId, boolean readd);

  /**
   * Metodo que devuelve el numero de libros que tiene para intercambio o no un
   * usuario.
   * 
   * @param userId define el id del usuario del cual se quieren saber el numero de
   *               libros.
   * @param trade  define si un libro esta para intercambio o no.
   * @return el numero de libros que tiene o no para intercambio un usuario.
   */
  public abstract int countByUserIdAndTrade(int userId, boolean trade);

  /**
   * Metodo que devuelve una lista de libros segun el valor del atributo trade
   * (intercambio).
   * 
   * @param trade define si un libro esta para intercambio o no.
   * @return una lista de libros.
   */
  public abstract List<UserBook> findByTrade(boolean trade);

  /**
   * Metodo que devuelve una lista de libros segun el valor del atributo readd.
   * 
   * @param readd define si un libro esta leido o no.
   * @return una lista de libros.
   */
  public abstract List<UserBook> findByReadd(boolean readd);

  /**
   * Metodo que elimina un libro concreto de la coleccion de un usuario.
   * 
   * @param userId define el id del usuario de quien queremos eliminar el libro.
   * @param bookId define el libro a eliminar del usuario.
   * @return true o false.
   */
  public abstract boolean deleteByUserIdAndBookId(int userId, int bookId);

  /**
   * Metodo que devuelve el numero de libros total de la app segun el atributo
   * status.
   * 
   * @param status define si un libro esta activado o desactivado.
   * @return el numero de libros activos o desactivados de la app.
   */
  public abstract int countByStatus(boolean status);

  /**
   * Metodo que devuelve el numero de libros segun los atributos status y readd.
   * 
   * @param readd  define si un libro esta leido o pendiente.
   * @param status define si un libro esta activado o desactivado.
   * @return el numero de libros segun sus atributos status y readd.
   */
  public abstract int countByReaddAndStatus(boolean readd, boolean status);

  /**
   * Metodo que devuelve el numero de libros segun los atributos status y trade.
   * 
   * @param trade  define si un libro esta para intercambio o no.
   * @param status define si un libro esta activado o desactivado.
   * @return el numero de libros segun sus atributos status y trade.
   */
  public abstract int countByTradeAndStatus(boolean trade, boolean status);

  public abstract int getUserIdMoreRead();

  public abstract int getUserIdMorePageRead();

  public abstract int getTotalPagesReadFromUser(int userId);
}