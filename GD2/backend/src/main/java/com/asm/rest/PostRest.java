package com.asm.rest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.PostDAO;
import com.asm.dao.UserDAO;
import com.asm.dao.impl.PostDAOImpl;
import com.asm.dao.impl.UserDAOImpl;
import com.asm.dto.PostForm;
import com.asm.entity.Post;
import com.asm.entity.User;
import com.asm.utils.RestIO;

@WebServlet("/api/blogs/*")
public class PostRest extends HttpServlet {

  private static PostDAO postDAO = new PostDAOImpl();
  private static UserDAO userDAO = new UserDAOImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String type = req.getPathInfo();
    switch (type) {
      case "/search":
        String searchQuery = req.getParameter("query");
        List<Post> posts = postDAO.findByKeyword(searchQuery);
        RestIO.writeObject(resp, posts);
        break;
      case "/get-5-most-watched":
        List<Post> list = postDAO.get5MostWatched();
        RestIO.writeObject(resp, list);
        break;
      case "/get-by-page":
        String pageQuery = req.getParameter("page");
        String sizeQuery = req.getParameter("size");

        if (pageQuery != null && sizeQuery != null) {
          int pageNumber = Integer.parseInt(pageQuery);
          int pageSize = Integer.parseInt(sizeQuery);
          List<Post> pagedPosts = postDAO.getPostsPagedAndSorted(pageNumber, pageSize);
          Map<String, Object> data = new HashMap<>();
          data.put("page", pageNumber);
          data.put("size", pageSize);
          data.put("totalPosts", postDAO.getTotlalPosts());
          data.put("totalPages", (int) Math.ceil((double) postDAO.getTotlalPosts() / pageSize));
          data.put("posts", pagedPosts);
          RestIO.writeObject(resp, data);
        } else {
          resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        break;

      case "/get-blog":
        String id = req.getParameter("id");
        if (id != null) {
          long postId = Long.parseLong(id);
          Post post = postDAO.findById(postId);
          RestIO.writeObject(resp, post);
        }
        break;
      case "/get-by-userid":
        String userId = req.getParameter("userId");
        List<Post> userList = postDAO.findByUserId(userId);
        RestIO.writeObject(resp, userList);
      default:

        break;
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PostForm postForm = RestIO.readObject(req, PostForm.class);

    User user = userDAO.findById(postForm.getUsername());

    Post post = new Post();
    post.setTitle(postForm.getTitle());
    post.setContent(postForm.getContent());
    post.setImage(postForm.getImage());
    post.setUser(user);
    post.setView(0);
    post.setCreatedAt(LocalDateTime.now());
    post.setUpdatedAt(LocalDateTime.now());

    Map<String, Object> result = new HashMap<>();

    try {
      postDAO.create(post);
      result.put("status", true);
      RestIO.writeObject(resp, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", false);
      RestIO.writeObject(resp, result);
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String type = req.getPathInfo();

    switch (type) {
      case "/update-blog":
        PostForm postForm = RestIO.readObject(req, PostForm.class);

        Post post = postDAO.findById(Long.parseLong(postForm.getId()));

        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setImage(postForm.getImage());
        post.setUpdatedAt(LocalDateTime.now());

        Map<String, Object> result = new HashMap<>();

        try {
          postDAO.update(post);
          result.put("status", true);
          RestIO.writeObject(resp, result);
        } catch (Exception e) {
          e.printStackTrace();
          result.put("status", false);
          RestIO.writeObject(resp, result);
        }
        break;
      case "/update-view":
        String id = req.getParameter("blogId");
        Map<String, Object> res = new HashMap<>();

        try {
          Long blogId = Long.parseLong(id);
          postDAO.increaseWatchCount(blogId);
          res.put("status", true);
          RestIO.writeObject(resp, res);
        } catch (Exception e) {
          e.printStackTrace();
          res.put("status", false);
          RestIO.writeObject(resp, res);
        }
        break;
      default:
        break;
    }

  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String id = req.getParameter("blogId");

    Map<String, Object> result = new HashMap<>();

    try {
      postDAO.deleteById(Long.parseLong(id));
      result.put("status", true);
      RestIO.writeObject(resp, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", false);
      RestIO.writeObject(resp, result);
    }
  }
}
