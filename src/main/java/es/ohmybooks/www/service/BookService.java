package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import es.ohmybooks.www.entity.Book;

public interface BookService {

  /**
   * Metodo que devuelve todos los libros de la app.
   * 
   * @return una lista de objetos libro.
   */
  public abstract List<Book> listBooks();

  /**
   * Metodo que guarda un objeto libro en la DB.
   * 
   * @param book define el objeto libro a guardar.
   * @return true or false
   */
  public abstract Book save(Book book);

  /**
   * Metodo que devuelve un libro segun un id concreto.
   * 
   * @param id identificador del libro a buscar.
   * @return un objeto libro.
   */
  public abstract Optional<Book> findById(int id);

  /**
   * Metodo que devuelve una lista de libros segun nombre pasado por parametro.
   * 
   * @param name define el titulo de los libros que se quiern filtar.
   * @return una lista de objetos libro.
   */
  public abstract List<Book> findByName(String name);

  /**
   * Metodo que devuelve una lista de libros segun el autor pasado por parametro.
   * 
   * @param author define el autor de los libros que se quieren filtrar.
   * @return una lista de objetos libro.
   */
  public abstract List<Book> findByAuthor(String author);

  /**
   * Metodo que elimina un libro de la DB.
   * 
   * @param id identificador del libro a eliminar.
   * @return true or false.
   */
  public abstract boolean deleteBookById(int id);

  /**
   * Metodo que comprueba si un libro existe en la DB segun su id.
   * 
   * @param id identificador del libro a comprobar.
   * @return true or false.
   */
  public boolean existsById(int id);

  /**
   * Metodo que comprueba si existe algun libro con un nombre concreto.
   * 
   * @param name define el nombre a buscar en los libros.
   * @return true o false
   */
  public boolean existsByName(String name);

  /**
   * Metodo que comprueba si existe algun libro con un autor concreto.
   * 
   * @param author define el autor a buscar e los libros.
   * @return true o false
   */
  public boolean existsByAuthor(String author);

  /**
   * Metodo que busca un libro con un nombre y autor concreto.
   * 
   * @param name   define el nombre del libro a buscar.
   * @param Author define el autor del libro a buscar.
   * @return
   */
  public abstract Book findByNameAndAuthor(String name, String Author);

}