package com.revature.readawaybackend.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {
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
