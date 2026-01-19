package com.asm.dao;

import java.util.List;

import com.asm.entity.Post;

public interface PostDAO extends CrudDAO<Post, Long> {
  List<Post> findByUserId(String userId);

  List<Post> getPostsPagedAndSorted(int pageNumber, int pageSize);

  List<Post> get5MostWatched();

  List<Post> findByKeyword(String keyword);

  int getTotlalPosts();

  void increaseWatchCount(Long postId);
}
