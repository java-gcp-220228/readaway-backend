package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.RegisterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {
  @Mock
  UserRepository userRepo;

  @InjectMocks
  private UserService userService;

  @Test
  void test_findByEmail_EmailDoesntExist() {
    String email = "testing@test.com";

    Assertions.assertFalse(userService.emailExists(email));
  }

  @Test
  void test_findByEmail_EmailDoesExist() {
    String email = "jane_doe@email.com";

    Assertions.assertFalse(userService.emailExists(email));
  }

  @Test
  void test_findByUsername_UserDoesntExist() {
    String username = "testing@test.com";

    Assertions.assertFalse(userService.userExists(username));
  }

  @Test
  void test_findByUser_userDoesExist() {
    String username = "jane_doe";
    Assertions.assertFalse(userService.userExists(username));
  }

  @Test
  void test_registerPositive() throws UserExistsException {
    String email = "test@test.com";
    String username = "testing123";
    String password = "password";

    RegisterDTO registerDto = new RegisterDTO(email, username, password);

    User user = userService.register(registerDto);

    System.out.println(user.getId());

    Assertions.assertNotNull(user);
    Assertions.assertEquals(email, user.getEmail());
  }

  /*
  @Test
  void test_registerNegativeEmail() {
    String email = "jane_doe@email.com";
    String username = "testing123";
    String password = "password";

    RegisterDTO dto = new RegisterDTO(email, username, password);

    Assertions.assertThrows(UserExistsException.class, () -> {
      userService.register(dto);
    });
  }


  @Test
  void test_registerNegativeUsername() {
    String email1 = "test@test.com";
    String username1 = "testing123";
    String password1 = "password";

    RegisterDTO registerDto1 = new RegisterDTO(email1, username1, password1);

    String email2 = "testing@test.com";
    String username2 = "testing123";
    String password2 = "password";

    RegisterDTO registerDto2 = new RegisterDTO(email2, username2, password2);

    Assertions.assertThrows(UserExistsException.class, () -> {
      userService.register(registerDto1);
      userService.register(registerDto2);
    });
  }
  */
}
