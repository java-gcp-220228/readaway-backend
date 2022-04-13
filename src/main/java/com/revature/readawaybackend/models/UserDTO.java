package com.revature.readawaybackend.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class UserDTO {
  @NotNull
  @Size(min = 1)
  private String username;
  @NotNull
  @Size(min = 1)
  private String password;
  @NotNull
  @Size(min = 1)
  private String email;
}
