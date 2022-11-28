package es.ohmybooks.www.service;

import java.util.ArrayList;
import java.util.List;

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
  public Collectionn save(Collectionn collection) {
    return collectionRepository.save(collection);
  }

  @Override
  public boolean deleteCollectionByUserId(int userId) {
    try {
      collectionRepository.deleteByUserId(userId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public List<Collectionn> findByUserId(int userId) {
    return collectionRepository.findByUserId(userId);
  }

  @Override
  public List<Collectionn> findByUserIdAndHide(int userId, boolean hide) {
    return collectionRepository.findByUserIdAndHide(userId, hide);
  }

  @Override
  public List<Collectionn> findByBookId(int bookId) {
    List<Collectionn> col = collectionRepository.findByBookId(bookId);
    List<Collectionn> filterCollection = new ArrayList<>();
    for (Collectionn c : col) {
      if (c.isStatus()==true) {
        filterCollection.add(c);
      }
    }
    return filterCollection;
  }

  @Override
  public boolean existsById(int id) {
    return collectionRepository.existsById(id);
  }

  @Override
  public Collectionn findByUserIdAndBookId(int userId, int bookId) {
    return collectionRepository.findByUserIdAndBookId(userId, bookId);
  }


  public void changeStatusByUserId(int userId) {
    List<Collectionn> listCol = collectionRepository.findByUserId(userId);
    for (Collectionn c : listCol) {
      if (c.isStatus() == false) {
        c.setStatus(true);
      } else {
        c.setStatus(false);
      }
      collectionRepository.save(c);
    }
  }

}