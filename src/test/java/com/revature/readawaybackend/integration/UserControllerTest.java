package com.revature.readawaybackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.readawaybackend.models.LoginDTO;
import com.revature.readawaybackend.models.RegisterDTO;
import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.headers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void test_loginEndpoint() throws Exception {
    LoginDTO dto = new LoginDTO();
    dto.setUsername("john_doe");
    dto.setPassword("pass");
    String jsonDto = (new ObjectMapper()).writeValueAsString(dto);

    User expected = new User();
    expected.setId(1);
    expected.setUsername("john_doe");
    expected.setPassword("pass");
    expected.setEmail("john_doe@email.com");

    String expectedJson = (new ObjectMapper()).writeValueAsString(expected);

    this.mockMvc.perform(
            post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
        .andExpect(status().is(200))
        .andExpect(content().json(expectedJson));
  }

  @Test
  void test_registerEndpoint() throws Exception {
    RegisterDTO dto = new RegisterDTO();
    dto.setEmail("jdoe@email.com");
    dto.setUsername("jdoe");
    dto.setPassword("pass");
    String jsonDto = (new ObjectMapper()).writeValueAsString(dto);

    User expected = new User();
    expected.setId(3);
    expected.setUsername("jdoe");
    expected.setPassword("pass");
    expected.setEmail("jdoe@email.com");

    String expectedJson = (new ObjectMapper()).writeValueAsString(expected);

    this.mockMvc.perform(
            post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
        .andExpect(status().is(200)).andExpect(content().json(expectedJson));
  }
}
