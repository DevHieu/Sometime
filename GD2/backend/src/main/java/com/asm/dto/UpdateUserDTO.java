package com.asm.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
  private String username;
  private String fullname;
  private String email;
  private String avatar;
}
