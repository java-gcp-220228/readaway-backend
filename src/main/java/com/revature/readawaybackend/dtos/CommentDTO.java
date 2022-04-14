package com.revature.readawaybackend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CommentDTO {
    int id;
    String text;
    Timestamp postTime;
    UserDTO user;
    Set<CommentDTO> replies;
}
