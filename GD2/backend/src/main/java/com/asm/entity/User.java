package com.asm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "fullname")
  private String fullname;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "avatar")
  private String avatar;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Post> posts = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Comment> comments;
}
