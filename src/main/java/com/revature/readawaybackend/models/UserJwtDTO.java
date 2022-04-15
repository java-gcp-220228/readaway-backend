package com.revature.readawaybackend.models;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserJwtDTO {
  private int id;
  private String username;
  private String email;
}