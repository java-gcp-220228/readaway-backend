package com.revature.readawaybackend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
  int id;
  String username;
  String email;
}
