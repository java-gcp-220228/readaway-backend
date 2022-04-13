package com.revature.readawaybackend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.readawaybackend.exceptions.UserExistsException;
import com.revature.readawaybackend.models.LoginDTO;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.UserDTO;
import com.revature.readawaybackend.service.AuthService;
import com.revature.readawaybackend.service.JwtService;
import com.revature.readawaybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDTO dto) throws JsonProcessingException {
    try {
      User user = authService.login(dto.getUsername(), dto.getPassword());
      String jwt = jwtService.createJwt(user);

      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("token", jwt);

      return ResponseEntity.ok().headers(responseHeaders).body(user);
    } catch (FailedLoginException e) {
      return ResponseEntity.status(401).body(e.getMessage());
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDTO dto) throws JsonProcessingException {
    try {
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
