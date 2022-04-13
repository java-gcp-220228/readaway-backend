package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepo;

  public User login(String username, String password) throws FailedLoginException {
    if(username.trim().equals("") || password.trim().equals("")) {
      throw new IllegalArgumentException("Enter a username/password");
    }
    User user = userRepo.findByUsernameAndPassword(username.trim(), password.trim());

    if(user == null) {
      throw new FailedLoginException("Invalid information entered");
    }
    return user;
  }
}