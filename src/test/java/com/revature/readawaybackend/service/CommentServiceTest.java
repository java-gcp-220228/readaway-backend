package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.CommentRepository;
import com.revature.readawaybackend.models.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

  @Mock
  private CommentRepository commentRepository;

  @InjectMocks
  private CommentService commentService;

  @Test
  void addCommentAsReply() {
    Comment parent = new Comment();
    Comment reply = new Comment();

    when(commentRepository.findById(1)).thenReturn(Optional.of(parent));

    Assertions.assertDoesNotThrow(() -> commentService.addCommentAsReply("1", reply));
  }

  @Test
  void addCommentAsReply_invalidParentId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      commentService.addCommentAsReply("abc", null);
    });
  }
}
