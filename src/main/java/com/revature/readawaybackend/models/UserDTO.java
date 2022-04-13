package com.revature.readawaybackend.models;

import lombok.*;

@NoArgsConstructor
@Data
public class UserDTO {
  private int id;
  private String username;
  private String email;
}
