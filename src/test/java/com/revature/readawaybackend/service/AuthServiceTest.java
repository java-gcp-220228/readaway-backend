package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
  @Mock
  private UserRepository userRepo;

  @InjectMocks
  private AuthService authService;

  @Test
  void test_loginValidPositive() throws FailedLoginException {
    User mockUser = new User();
    mockUser.setId(20);
    mockUser.setEmail("test@test.com");
    mockUser.setUsername("test");
    mockUser.setPassword("password");

    when(userRepo.findByUsernameAndPassword("test", "password")).thenReturn(mockUser);

    User actual = authService.login("test", "password");
    User expected = new User(20, "test", "password", "test@test.com");

    Assertions.assertEquals(expected, actual);
  }

//  @Test
//  void test_loginValidNegative() throws FailedLoginException {
//  test that login fails when incorrect information is given
//  }
}
