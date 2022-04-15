package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.Comment;
import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CommentRepositoryTest {

  @Autowired
  CommentRepository commentRepo;

  @Test
  void test_getCommentById_positive() {
    Comment actual = commentRepo.findById(1).get();

    User user = new User();
    user.setId(1);
    user.setUsername("john_doe");
    user.setPassword("pass");
    user.setEmail("john_doe@email.com");

    Comment expected = new Comment();
    expected.setId(1);
    expected.setText("1");
    expected.setPostTime(Timestamp.valueOf("2022-04-13 00:00:00"));
    expected.setUser(user);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void test_getCommentById_notFound() {
    Assertions.assertFalse(commentRepo.findById(100).isPresent());
  }

  @Test
  void test_addCommentAsReply() {
    User user = new User();
    user.setId(1);
    user.setUsername("john_doe");
    user.setPassword("pass");
    user.setEmail("john_doe@email.com");

    Comment reply = new Comment();
    reply.setText("reply");
    reply.setPostTime(Timestamp.valueOf("2022-04-17 00:00:00"));
    reply.setUser(user);

    Comment parent = new Comment();
    parent.setId(1);
    parent.setText("1");
    parent.setPostTime(Timestamp.valueOf("2022-04-13 00:00:00"));
    parent.setUser(user);
    Set<Comment> replies = new LinkedHashSet<>();
    replies.add(reply);
    parent.setReplies(replies);

    commentRepo.save(parent);

    Comment actual = commentRepo.findById(1).get().getReplies().iterator().next();
    Comment expected = reply;
    expected.setId(5);

    Assertions.assertEquals(expected, actual);
  }
}
