package com.asm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.asm.dao.CommentDAO;
import com.asm.dto.CommentDetailDTO;
import com.asm.entity.Comment;
import com.asm.utils.XJpa;

public class CommentDAOImpl implements CommentDAO {

  EntityManager em = XJpa.getEntityManager();

  @Override
  public List<Comment> findAll() {
    String jpql = "SELECT c FROM Comment c";
    return em.createQuery(jpql, Comment.class).getResultList();
  }

  @Override
  public Comment findById(Long id) {
    return em.find(Comment.class, id);
  }

  @Override
  public void create(Comment item) {
    em.getTransaction().begin();
    em.persist(item);
    em.getTransaction().commit();
  }

  @Override
  public void update(Comment item) {
    em.getTransaction().begin();
    em.merge(item);
    em.getTransaction().commit();
  }

  @Override
  public void deleteById(Long id) {
    Comment comment = em.find(Comment.class, id);
    if (comment != null) {
      em.getTransaction().begin();
      em.remove(comment);
      em.getTransaction().commit();
    }
  }

  @Override
  public List<CommentDetailDTO> findByPostId(Long postId) {
    String jpql = "SELECT new com.asm.dto.CommentDetailDTO(c.content, c.user.fullname, c.user.avatar) FROM Comment c WHERE c.post.id = :postId";
    return em.createQuery(jpql, CommentDetailDTO.class)
        .setParameter("postId", postId)
        .getResultList();
  }

}
