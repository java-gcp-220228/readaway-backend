package com.revature.readawaybackend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentDTO {
    int id;
    String text;
    Timestamp postTime;
    UserDTO user;
    List<CommentDTO> replies;
}
