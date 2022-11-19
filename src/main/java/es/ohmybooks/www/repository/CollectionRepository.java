package es.ohmybooks.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

  Optional<Collection> findByIdUser(int idUser);
  
  Optional<Collection> findByIdBook(int idBook);

  boolean existsByIdUser(int idUser);

  boolean existsByIdBook(int idBook);

}
