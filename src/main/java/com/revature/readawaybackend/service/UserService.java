package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
  @Autowired
  UserRepository userRepo;
  @Autowired
  private PasswordEncoder passwordEncoder;
  public User register(UserDTO dto) throws UserExistsException {
    if(emailExists(dto.getEmail())) {
      throw new UserExistsException("An account exists with email address" + dto.getEmail());
    }

    if(userExists(dto.getUsername())) {}

    User user = new User();

    user.setEmail(dto.getEmail());
    user.setUsername(dto.getUsername());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    // save or saveAndFlush?
    return userRepo.save(user);
  }

  private boolean emailExists(String email) {
    return userRepo.findByEmail(email) != null;
  }

  private boolean userExists(String username) {
    return userRepo.findByUsername(username) != null;
  }
}
