package com.asm.dao;

import java.util.List;

import com.asm.dto.CommentDetailDTO;
import com.asm.entity.Comment;

public interface CommentDAO extends CrudDAO<Comment, Long> {
  List<CommentDetailDTO> findByPostId(Long postId);
}
