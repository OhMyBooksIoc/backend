package es.ohmybooks.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ohmybooks.www.entity.UserBook;
import es.ohmybooks.www.repository.UserBookRepository;

@Service
@Transactional
public class UserBookServiceImpl implements UserBookService {

  @Autowired
  UserBookRepository userBookRepository;

  @Override
  public UserBook save(UserBook userBook) {
    return userBookRepository.save(userBook);
  }

  @Override
  public boolean deleteRelationUserBook(int userId) {
    try {
      userBookRepository.deleteByUserId(userId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public List<UserBook> findByUserId(int userId) {
    return userBookRepository.findByUserId(userId);
  }

  @Override
  public List<UserBook> findByUserIdAndHide(int userId, boolean hide) {
    return userBookRepository.findByUserIdAndHide(userId, hide);
  }

  @Override
  public List<UserBook> findByBookId(int bookId) {
    List<UserBook> col = userBookRepository.findByBookId(bookId);
    List<UserBook> filter = new ArrayList<>();
    for (UserBook c : col) {
      if (c.isStatus()==true) {
        filter.add(c);
      }
    }
    return filter;
  }

  @Override
  public boolean existsById(int id) {
    return userBookRepository.existsById(id);
  }

  @Override
  public UserBook findByUserIdAndBookId(int userId, int bookId) {
    return userBookRepository.findByUserIdAndBookId(userId, bookId);
  }

  @Override
  public List<UserBook> findByTrade(boolean trade) {
    return userBookRepository.findByTrade(trade);
  }

  @Override
  public int countByUserId(int userId) {
    return userBookRepository.countByUserId(userId);
  }

  @Override
  public boolean deleteByUserIdAndBookId(int userId, int bookId) {
    try {
      userBookRepository.deleteByUserIdAndBookId(userId, bookId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public long count() {
    return userBookRepository.count();
  }

  @Override
  public int countByReadd(boolean readd) {
    return userBookRepository.countByReadd(readd);
  }

  @Override
  public int countByTrade(boolean trade) {
    return userBookRepository.countByTrade(trade);
  }


  public void changeStatusByUserId(int userId) {
    List<UserBook> listCol = userBookRepository.findByUserId(userId);
    for (UserBook c : listCol) {
      if (c.isStatus() == false) {
        c.setStatus(true);
      } else {
        c.setStatus(false);
      }
      userBookRepository.save(c);
    }
  }

}