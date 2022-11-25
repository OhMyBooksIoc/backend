package es.ohmybooks.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ohmybooks.www.entity.Collectionn;

@Repository
public interface CollectionRepository extends JpaRepository<Collectionn, Integer> {

  List<Collectionn> findByUserId(int userId);
  
  List<Collectionn> findByBookId(int bookId);

  boolean existsByUserId(int userId);

  boolean existsByBookId(int bookId);

}
