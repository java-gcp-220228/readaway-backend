package com.revature.readawaybackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.readawaybackend.models.User;
import com.revature.readawaybackend.models.UserJwtDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JWTServiceTest {

    private JwtService jwtService = new JwtService();

    @Test
    public void testCreateValidJwt() throws JsonProcessingException {
        String jwt = jwtService.createJwt(new User(1, "username", "password", "test@email.com"));

        Assertions.assertEquals(193, jwt.length());
    }

    @Test
    public void testCreatesDifferentJwtsForDifferentUsers() throws JsonProcessingException {

        String jwt1 = jwtService.createJwt(new User(1, "username", "password", "test@email.com"));
        String jwt2 = jwtService.createJwt(new User(2, "user_name", "pass_word", "test@email.com"));


        Assertions.assertNotEquals(jwt1, jwt2);
    }

    @Test
    public void testParseValidJWT() throws JsonProcessingException {
        User user = new User(1, "username", "password", "test@email.com");
        String jwt = jwtService.createJwt(user);


        UserJwtDTO dto  = jwtService.parseJwt(jwt);

        String username = dto.getUsername();
        Object id = dto.getId();
        Object email = dto.getEmail();

        Assertions.assertEquals(user.getId(), id);
        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertEquals(user.getEmail(), email);
    }

    @Test
    public void testParseInvalidJWT() throws JsonProcessingException {
        User user = new User(1, "username", "password", "test@email.com");
        String jwt = jwtService.createJwt(user);

        String invalidJwt = jwt.substring(0, jwt.length() -2) + "@";

        Assertions.assertThrows(Exception.class, () ->{
            jwtService.parseJwt(invalidJwt);
        });
    }
}
