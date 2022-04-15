package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  UserRepository userRepo;

  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserService userService;

  @Test
  void test_registerPositive() throws UserExistsException {
    String email = "test@test.com";
    String username = "testing123";
    String password = "password";

    UserDTO userDto = new UserDTO(email, username, password);

    User user = userService.register(userDto);

    System.out.println(user.getId());

    Assertions.assertNotNull(user);
    Assertions.assertEquals(email, user.getEmail());
    Assertions.assertNotNull(user.getId());
  }

  @Test
  void test_registerNegativeEmail() {
    Assertions.assertThrows(UserExistsException.class, () -> {
      String email = "test@test.com";
      String username = "testing123";
      String password = "password";

      UserDTO userDto = new UserDTO(email, username, password);
      userService.register(userDto);
      userService.register(userDto);
    });
  }

  @Test
  void test_registerNegativeUsername() throws UserExistsException {

  }

  @Test
  void encode() {
    String plainPassword = "password";

    String encoded = passwordEncoder.encode(plainPassword);

    assertTrue(encoded.startsWith("$2a$10"));
  }
}
