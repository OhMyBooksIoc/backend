package es.ohmybooks.www.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;

/**
 * Clase que define un objeto BookDto
 * 
 * @author Group3
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BookDto {

  @NotBlank
  private String name;
  private String author;
  private String genre;
  private String saga;
  private int year;
  private int pages;
  private String cover;

  /**
   * Metodo constructor por defecto
   */
  public BookDto() {
  }

  /**
   * Metodo constructor con todos los parametros
   * 
   * @param name titulo del libro
   * @param author autor del libro
   * @param genre género del libro
   * @param saga saga del libro
   * @param year año de primera edición del libro
   * @param pages paginas del libro
   * @param cover portada del libro
   */
  public BookDto(String name, String author, String genre, String saga, int year, int pages, String cover) {
    this.name = name;
    this.author = author;
    this.genre = genre;
    this.saga = saga;
    this.year = year;
    this.pages = pages;
    this.cover = cover;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getSaga() {
    return saga;
  }

  public void setSaga(String saga) {
    this.saga = saga;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

}
