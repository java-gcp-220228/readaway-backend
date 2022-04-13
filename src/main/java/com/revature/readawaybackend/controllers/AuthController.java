package com.revature.readawaybackend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.readawaybackend.models.LoginDTO;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.UserDTO;
import com.revature.readawaybackend.service.AuthService;
import com.revature.readawaybackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class AuthController {
  @Autowired
  AuthService authService;
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
  public ResponseEntity<?> register(@RequestBody User form) throws JsonProcessingException {
      User user = authService.register(form.getEmail(), form.getUsername(), form.getPassword());
      // set id for user is automatic?

      String jwt = jwtService.createJwt(user);

      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("token", jwt);

      return ResponseEntity.ok().headers(responseHeaders).body(user);

      // set catch blocks for existing username and existing email
  }
}
