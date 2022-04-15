package com.revature.readawaybackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.readawaybackend.models.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

  private Key key;

  public JwtService() {
    byte[] secret = "my_secret_secret_sdkghajkdfdfdfdfdfdfbjsdbtglaewoiejmnxmeiuwtg".getBytes();
    key = Keys.hmacShaKeyFor(secret);
  }

  public String createJwt(User user) throws JsonProcessingException {
    UserJwtDTO dto = new UserJwtDTO(user.getId(), user.getUsername(), user.getEmail());

    String jwt = Jwts.builder()
        .claim("user_dto", new ObjectMapper().writeValueAsString(dto))
        .signWith(key)
        .compact();

    return jwt;
  }

  public UserJwtDTO parseJwt(String jwt) throws JsonProcessingException {
    Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

    String dtoString = (String) token.getBody().get("user_dto");

    return (new ObjectMapper()).readValue(dtoString, UserJwtDTO.class);
  }
}