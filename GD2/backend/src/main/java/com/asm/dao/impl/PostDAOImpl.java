package com.asm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.asm.dao.PostDAO;
import com.asm.entity.Post;
import com.asm.utils.XJpa;

public class PostDAOImpl implements PostDAO {

  EntityManager em = XJpa.getEntityManager();

  @Override
  public List<Post> findAll() {
    String jpql = "SELECT p FROM Post p";
    return em.createQuery(jpql, Post.class).getResultList();
  }

  @Override
  public Post findById(Long id) {
    return em.find(Post.class, id);
  }

  @Override
  public void create(Post item) {
    em.getTransaction().begin();
    em.persist(item);
    em.getTransaction().commit();
  }

  @Override
  public void update(Post item) {
    em.getTransaction().begin();
    em.merge(item);
    em.getTransaction().commit();
  }

  @Override
  public void deleteById(Long id) {
    Post post = em.find(Post.class, id);
    if (post != null) {
      em.getTransaction().begin();
      em.remove(post);
      em.getTransaction().commit();
    }
  }

  @Override
  public List<Post> findByUserId(String userId) {
    String jpql = "SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.updatedAt DESC";
    return em.createQuery(jpql, Post.class)
        .setParameter("userId", userId)
        .getResultList();
  }

  @Override
  public List<Post> getPostsPagedAndSorted(int pageNumber, int pageSize) {
    String jpql = "SELECT p FROM Post p ORDER BY p.createdAt DESC";
    return em.createQuery(jpql, Post.class)
        .setFirstResult((pageNumber - 1) * pageSize)
        .setMaxResults(pageSize)
        .getResultList();
  }

  @Override
  public List<Post> get5MostWatched() {
    String jpql = "SELECT p FROM Post p ORDER BY p.view DESC";
    return em.createQuery(jpql, Post.class)
        .setMaxResults(5)
        .getResultList();
  }

  @Override
  public List<Post> findByKeyword(String keyword) {
    String jpql = "SELECT p FROM Post p WHERE p.title LIKE :keyword ORDER BY p.view DESC";
    return em.createQuery(jpql, Post.class)
        .setParameter("keyword", "%" + keyword + "%")
        .getResultList();
  }

  @Override
  public int getTotlalPosts() {
    String jpql = "SELECT COUNT(p) FROM Post p";
    Long count = em.createQuery(jpql, Long.class).getSingleResult();
    return count.intValue();
  }

  @Override
  public void increaseWatchCount(Long postId) {
    em.getTransaction().begin();
    Post post = em.find(Post.class, postId);
    if (post != null) {
      post.setView(post.getView() + 1);
      em.merge(post);
    }
    em.getTransaction().commit();
  }

}