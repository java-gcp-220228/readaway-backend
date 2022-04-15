package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.FailedLoginException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
  @Mock
  private UserRepository userRepo;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @InjectMocks
  private AuthService authService;

  @Test
  void test_loginValidPositive() throws FailedLoginException {
    User mockUser = new User();
    mockUser.setId(20);
    mockUser.setEmail("test@test.com");
    mockUser.setUsername("test");
    mockUser.setPassword(passwordEncoder.encode("password"));

    String encoded = passwordEncoder.encode("password");

    when(userRepo.findByUsernameAndPassword(eq("test"), eq(encoded))).thenReturn(mockUser);

    User actual = authService.login("test", encoded);
    User expected = mockUser;


    Assertions.assertEquals(expected, actual);
  }

  @Test
  void test_loginValidNegative() throws FailedLoginException {

  }

  @Test
  void encode() {
    String plainPassword = "password";

    String encoded = passwordEncoder.encode(plainPassword);
    System.out.println(encoded);

    assertTrue(encoded.startsWith("$2a$10"));
  }
}
