package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.entity.Collectionn;
import es.ohmybooks.www.repository.CollectionRepository;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

  @Autowired
  CollectionRepository collectionRepository;

  @Override
  public List<Collectionn> listCollection(){
    return collectionRepository.findAll();
  }

  @Override
  public Collectionn save(Collectionn collection) {
    return collectionRepository.save(collection);
  }

  @Override
  public boolean deleteCollectionById(int id) {
    try {
      collectionRepository.deleteById(id);
      return true;
    } catch (Exception e){ 
      return false;
    }
  }

  @Override
  public Optional<Collectionn> findByUserId(int userId) {
    return collectionRepository.findByUserId(userId);

  }

  @Override
  public Optional<Collectionn> findByBookId(int bookId) {
    return collectionRepository.findByBookId(bookId);
  }

  @Override
  public boolean existsById(int id) {
    return collectionRepository.existsById(id);
  }

}