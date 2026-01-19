package com.asm.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asm.dao.UserDAO;
import com.asm.dao.impl.UserDAOImpl;
import com.asm.dto.UpdatePasswordDTO;
import com.asm.dto.UpdateUserDTO;
import com.asm.entity.User;
import com.asm.utils.RestIO;

@WebServlet("api/user/*")
public class UserRest extends HttpServlet {

  private static UserDAO userDAO = new UserDAOImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String userId = req.getParameter("user-id");

    if (userId != null) {
      User user = userDAO.findById(userId);
      RestIO.writeObject(resp, user);
    } else {
      RestIO.writeObject(resp, userDAO.findAll());
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String type = req.getPathInfo();

    switch (type) {
      case "/update-info": {
        UpdateUserDTO data = RestIO.readObject(req, UpdateUserDTO.class);

        User user = userDAO.findById(data.getUsername());
        user.setFullname(data.getFullname());
        user.setEmail(data.getEmail());
        user.setAvatar(data.getAvatar());

        Map<String, Object> result = new HashMap<>();

        try {
          userDAO.update(user);

          result.put("status", true);
          result.put("user", user);
        } catch (Exception e) {
          result.put("status", false);
        }

        RestIO.writeObject(resp, result);
        break;
      }

      case "/change-password": {
        System.out.println("changing password");
        UpdatePasswordDTO data = RestIO.readObject(req, UpdatePasswordDTO.class);

        User user = userDAO.findById(data.getUsername());

        Map<String, Object> result = new HashMap<>();
        if (!user.getPassword().equals(data.getOldPassword())) {
          System.out.println("wrong password");
          result.put("status", false);
          result.put("message", "Mật khẩu cũ không đúng");
          RestIO.writeObject(resp, result);
          return;
        }

        user.setPassword(data.getNewPassword());
        try {
          userDAO.update(user);
          result.put("status", true);
          RestIO.writeObject(resp, result);
        } catch (Exception e) {
          result.put("status", false);
          RestIO.writeObject(resp, result);
        }
        break;
      }

      default:
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid API");
    }

  }

  // DELETE:/employees/ID
  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String userId = req.getParameter("user-id");
    userDAO.deleteById(userId);
    RestIO.writeEmptyObject(resp);
  }
}
