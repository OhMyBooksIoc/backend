package es.ohmybooks.www.service;

import java.util.List;

import es.ohmybooks.www.entity.Collectionn;

public interface CollectionService {

  public abstract Collectionn save(Collectionn collection);

  public abstract List<Collectionn> findByUserId(int userId);

  public abstract List<Collectionn> findByUserIdAndHide(int userId, boolean hide);

  public abstract List<Collectionn> findByBookId(int bookId);

  public abstract boolean deleteCollectionByUserId(int userId);

  public boolean existsById(int id);

  public void changeStatusByUserId(int userId);

  public Collectionn findByUserIdAndBookId(int userId, int bookId);


}