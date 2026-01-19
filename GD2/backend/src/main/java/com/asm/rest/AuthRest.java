package com.asm.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asm.dao.UserDAO;
import com.asm.dao.impl.UserDAOImpl;
import com.asm.dto.LoginDTO;
import com.asm.dto.RegisterDTO;
import com.asm.entity.User;
import com.asm.utils.RestIO;

@WebServlet("api/auth/*")
public class AuthRest extends HttpServlet {

  private static UserDAO userDAO = new UserDAOImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.getWriter().print("Auth API is working");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String type = req.getPathInfo();
    Map<String, Object> result = new HashMap<String, Object>();
    if (type.equals("/login")) {
      LoginDTO user = RestIO.readObject(req, LoginDTO.class);
      int status = userDAO.checkLogin(user.getUsername(), user.getPassword());

      if (status == 1) {
        HttpSession session = req.getSession();
        User userDetail = userDAO.findById(user.getUsername());
        session.setAttribute("user", userDetail);
        result.put("user", userDetail);
      }

      result.put("status", status);
      RestIO.writeObject(resp, result);
    } else if (type.equals("/register")) {
      RegisterDTO registerInfo = RestIO.readObject(req, RegisterDTO.class);
      User userEntity = new User();

      userEntity.setUsername(registerInfo.getUsername());
      userEntity.setFullname(registerInfo.getFullname());
      userEntity.setPassword(registerInfo.getPassword());
      userEntity.setEmail(registerInfo.getEmail());

      Map<String, Object> registerResult = new HashMap<>();

      if (userDAO.checkUserExisted(userEntity.getUsername(), userEntity.getEmail())) {
        userDAO.create(userEntity);
        registerResult.put("status", 1);
        registerResult.put("user", userEntity);
        RestIO.writeObject(resp, registerResult);
      } else {
        registerResult.put("status", 0);
        RestIO.writeObject(resp, registerResult);
      }

    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
