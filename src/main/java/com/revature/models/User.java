package com.revature.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "username", nullable = false, length = 50)
  private String username;

  @Column(name = "password", nullable = false, length = 50)
  private String password;

  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @ManyToMany(mappedBy = "users")
  private Set<Giveaway> entries = new HashSet<>();

  public User() {
  }

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public User(int id, String username, String password, String email) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public User(int id, String username, String password, String email, Set<Giveaway> entries) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.entries = entries;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Giveaway> getEntries() {
    return entries;
  }

  public void setEntries(Set<Giveaway> entries) {
    this.entries = entries;
  }

  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof User)) return false;
    if (!super.equals(object)) return false;
    User user = (User) object;
    return id == user.id && username.equals(user.username) && password.equals(user.password) && email.equals(user.email) && java.util.Objects.equals(entries, user.entries);
  }

  public int hashCode() {
    return java.util.Objects.hash(super.hashCode(), id, username, password, email, entries);
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", entries=" + entries +
        '}';
  }
}
