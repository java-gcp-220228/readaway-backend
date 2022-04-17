package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.UserRepository;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepo;

  private boolean emailExists(String email) {
    return userRepo.findByEmail(email) != null;
  }

  private boolean userExists(String username) {
    return userRepo.findByUsername(username) != null;
  }

  public User register(RegisterDTO dto) throws UserExistsException {
    if (emailExists(dto.getEmail())) {
      throw new UserExistsException("An account exists with email address " + dto.getEmail());
    }
    if (userExists(dto.getUsername())) {
      throw new UserExistsException("An account exists with username " + dto.getUsername());
    }

    String plainPswd = dto.getPassword();
    String hashed = BCrypt.hashpw(plainPswd, BCrypt.gensalt());

    User user = new User();

    user.setEmail(dto.getEmail());
    user.setUsername(dto.getUsername());
    user.setPassword(hashed);
    // save or saveAndFlush?
    userRepo.saveAndFlush(user);

    return user;
  }
}
