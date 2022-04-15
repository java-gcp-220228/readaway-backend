package com.revature.readawaybackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.*;
import com.revature.readawaybackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class UserController {
  @Autowired
  AuthService authService;
  @Autowired
  UserService userService;
  @Autowired
  JwtService jwtService;

  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDTO dto) throws JsonProcessingException {
    try {
      User user = authService.login(dto.getUsername(), passwordEncoder.encode(dto.getPassword()));;
      String jwt = jwtService.createJwt(user);

      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("token", jwt);

      return ResponseEntity.ok().headers(responseHeaders).body(user);
    } catch (FailedLoginException e) {
      return ResponseEntity.status(401).body(e.getMessage());
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterDTO dto) throws JsonProcessingException {
    try {
      String encode = passwordEncoder.encode(dto.getPassword());
      RegisterDTO register = new RegisterDTO(dto.getEmail(), dto.getUsername(), encode);
      User registered = userService.register(dto);
      String jwt = jwtService.createJwt(registered);

      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("token", jwt);

      return ResponseEntity.ok().headers(responseHeaders).body(registered);
    } catch (UserExistsException e) {
      return ResponseEntity.status(409).body(e.getMessage());
    }
  }
}
