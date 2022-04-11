package com.revature.readawaybackend.models;

import lombok.*;

@NoArgsConstructor
@Data
public class LoginDTO {
  private String username;
  private String password;
}
