package com.asm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.asm.dao.UserDAO;
import com.asm.entity.User;
import com.asm.utils.XJpa;

public class UserDAOImpl implements UserDAO {

  EntityManager em = XJpa.getEntityManager();

  @Override
  public List<User> findAll() {
    String jpql = "SELECT u FROM User u";
    return em.createQuery(jpql, User.class).getResultList();
  }

  @Override
  public User findById(String id) {
    return em.find(User.class, id);
  }

  @Override
  public void create(User item) {
    em.getTransaction().begin();
    em.persist(item);
    em.getTransaction().commit();
  }

  @Override
  public void update(User item) {
    em.getTransaction().begin();
    em.merge(item);
    em.getTransaction().commit();
  }

  @Override
  public void deleteById(String id) {
    User user = em.find(User.class, id);
    if (user != null) {
      em.getTransaction().begin();
      em.remove(user);
      em.getTransaction().commit();
    }
  }

  @Override
  public int checkLogin(String userId, String password) {
    User user = em.find(User.class, userId);
    if (user == null) {
      return 0; // User not found
    } else if (user.getPassword().equals(password)) {
      return 1; // Login successful
    } else {
      return -1; // Incorrect password
    }
  }

  @Override
  public boolean checkUserExisted(String username, String email) {
    String jpql = "SELECT COUNT(u) FROM User u WHERE u.username = :username OR u.email = :email";
    Long count = em.createQuery(jpql, Long.class)
        .setParameter("username", username)
        .setParameter("email", email)
        .getSingleResult();
    return count == 0;
  }

}
