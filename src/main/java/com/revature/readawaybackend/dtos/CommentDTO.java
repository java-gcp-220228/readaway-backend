package com.revature.readawaybackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
public class CommentDTO {
  int id;
  String text;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  Timestamp postTime;
  UserDTO user;
  Set<CommentDTO> replies;
}
