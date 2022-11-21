package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import es.ohmybooks.www.entity.Collection;

public interface CollectionService {

  public abstract List<Collection> listCollection();

  public abstract Collection save(Collection collection);

  public abstract Optional<Collection> findByIdUser(int idUser);

  public abstract Optional<Collection> findByIdBook(int idBook);

  public abstract boolean deleteCollectionById(int id);

  public boolean existsById(int id);

}