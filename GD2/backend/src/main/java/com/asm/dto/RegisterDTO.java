package com.asm.dto;

import lombok.Data;

@Data
public class RegisterDTO {
  private String username;
  private String fullname;
  private String password;
  private String email;
  private String avatar;
}
