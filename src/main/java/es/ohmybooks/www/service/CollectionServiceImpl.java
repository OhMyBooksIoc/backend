package es.ohmybooks.www.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.entity.Collection;
import es.ohmybooks.www.repository.CollectionRepository;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

  @Autowired
  CollectionRepository collectionRepository;

  @Override
  public List<Collection> listCollection(){
    return collectionRepository.findAll();
  }

  @Override
  public Collection save(Collection collection) {
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
  public Optional<Collection> findByIdUser(int idUser) {
    return collectionRepository.findByIdUser(idUser);

  }

  @Override
  public Optional<Collection> findByIdBook(int idBook) {
    return collectionRepository.findByIdBook(idBook);
  }

  @Override
  public boolean existsById(int id) {
    return collectionRepository.existsById(id);
  }

}