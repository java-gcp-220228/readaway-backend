package com.revature.readawaybackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class GiveawayDTO {
  int id;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  Timestamp startTime;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  Timestamp endTime;
  String isbn;
  UserDTO creator;
  UserDTO winner;
  List<CommentDTO> comments;
  Set<UserDTO> entrants;
}
