package es.ohmybooks.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.Collectionn;

@Repository
public interface CollectionRepository extends JpaRepository<Collectionn, Integer> {

  Optional<Collectionn> findByIdUser(int idUser);
  
  Optional<Collectionn> findByIdBook(int idBook);

  boolean existsByIdUser(int idUser);

  boolean existsByIdBook(int idBook);

}
