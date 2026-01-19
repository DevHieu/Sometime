package com.asm.dto;

import lombok.Data;

@Data
public class PostForm {
  private String id;
  private String title;
  private String content;
  private String image;
  private String username;
}
