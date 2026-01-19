package com.asm.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.CommentDAO;
import com.asm.dao.impl.CommentDAOImpl;
import com.asm.dto.CommentDetailDTO;
import com.asm.dto.NewCommentDTO;
import com.asm.entity.Comment;
import com.asm.entity.Post;
import com.asm.entity.User;
import com.asm.utils.RestIO;

@WebServlet("api/comment/*")
public class CommentRest extends HttpServlet {

  private static CommentDAO commentDAO = new CommentDAOImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String type = req.getPathInfo();

    switch (type) {
      case "/get-by-post-id":
        String postId = req.getParameter("blogId");
        Map<String, Object> result = new HashMap<>();

        try {
          List<CommentDetailDTO> comments = commentDAO.findByPostId(Long.parseLong(postId));
          result.put("status", true);
          result.put("comments", comments);
          RestIO.writeObject(resp, result);
        } catch (Exception e) {
          result.put("status", false);
          RestIO.writeObject(resp, result);
        }
        break;

      default:

        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    NewCommentDTO newComment = RestIO.readObject(req, NewCommentDTO.class);

    Post postRef = new Post();
    postRef.setId(Long.parseLong(newComment.getBlogId()));

    User userRef = new User();
    userRef.setUsername(newComment.getUsername());

    Comment comment = new Comment();
    comment.setContent(newComment.getContent());
    comment.setPost(postRef);
    comment.setUser(userRef);

    Map<String, Object> result = new HashMap<>();

    try {
      commentDAO.create(comment);
      result.put("status", true);
      RestIO.writeObject(resp, result);
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", false);
      RestIO.writeObject(resp, result);
    }
  }
}
