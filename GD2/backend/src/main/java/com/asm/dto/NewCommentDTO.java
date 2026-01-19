package com.asm.dto;

import lombok.Data;

@Data
public class NewCommentDTO {
  private String content;
  private String blogId;
  private String username;
}
