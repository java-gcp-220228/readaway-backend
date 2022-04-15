
package com.revature.readawaybackend.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {
  private String email;
  private String username;
  private String password;
}