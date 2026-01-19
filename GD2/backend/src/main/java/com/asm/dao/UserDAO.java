package com.asm.dao;

import com.asm.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
  int checkLogin(String userId, String password);

  boolean checkUserExisted(String username, String email);
}
