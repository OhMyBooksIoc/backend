package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import es.ohmybooks.www.entity.Collectionn;

public interface CollectionService {

  public abstract List<Collectionn> listCollection();

  public abstract Collectionn save(Collectionn collection);

  public abstract Optional<Collectionn> findByIdUser(int idUser);

  public abstract Optional<Collectionn> findByIdBook(int idBook);

  public abstract boolean deleteCollectionById(int id);

  public boolean existsById(int id);

}