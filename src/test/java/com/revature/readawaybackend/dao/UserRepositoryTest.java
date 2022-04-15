package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepo;

  // test login for user
  @Test
  public void test_UsernamePasswordPositive() {
    User actual = userRepo.findByUsernameAndPassword("john_doe", "pass");

    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_UsernamePasswordNegative() {
    User actual = userRepo.findByUsernameAndPassword("jane_doe", "123");

    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertNotEquals(actual, expected);
  }

  @Test
  public void test_UsernamePasswordBlankNegative() {
    User actual = userRepo.findByUsernameAndPassword("  ", "   ");

    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertNotEquals(actual, expected);
  }

  @Test
  void test_findByEmailPositive() {
    User actual = userRepo.findByEmail("john_doe@email.com");
    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertEquals(actual, expected);
  }

  @Test
  void test_findByEmailNegative() {
    User actual = userRepo.findByEmail("test@email.com");
    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertNotEquals(actual, expected);
  }

  @Test
  void test_findByUsernamePositive() {
    User actual = userRepo.findByUsername("john_doe");
    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertEquals(actual, expected);
  }

  @Test
  void test_findByUsernameNegative() {
    User actual = userRepo.findByUsername("johnn_doee");
    User expected = new User(1, "john_doe", "pass", "john_doe@email.com");

    Assertions.assertNotEquals(actual, expected);
  }
}
