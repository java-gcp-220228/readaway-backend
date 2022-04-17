//package com.revature.readawaybackend.integration;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.readawaybackend.dtos.CommentDTO;
//import com.revature.readawaybackend.dtos.GiveawayDTO;
//import com.revature.readawaybackend.dtos.UserDTO;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class GiveawayControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeAll
//    void setup() {
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
//    }
//
//    @Test
//    void test_GetGiveawayByValidId() throws Exception {
//
//        UserDTO user = new UserDTO();
//        user.setId(1);
//        user.setUsername("john_doe");
//        user.setEmail("john_doe@email.com");
//
//        GiveawayDTO expected = new GiveawayDTO();
//        expected.setId(1);
//        expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setIsbn("1234567890");
//        expected.setCreator(user);
//        Set<UserDTO> set = new HashSet<>();
//        expected.setEntrants(set);
//        Set<CommentDTO> comments = new LinkedHashSet<>();
//        expected.setComments(comments);
//
//        String expectedJSON = mapper.writeValueAsString(expected);
//
//        this.mockMvc.perform(get("/giveaways/{giveaway_id}", "1"))
//                .andExpect(status().is(200))
//                .andExpect(content().json(expectedJSON));
//
//    }
//
//    @Test
//    void test_getGiveawayNotFound() throws Exception {
//        this.mockMvc.perform(get("/giveaways/{giveaway_id}", 100))
//                .andExpect(status().is(404))
//                .andExpect(content().string("404 NOT_FOUND"));
//    }
//
//    @Test
//    void test_getGiveawayByInvalidId_NumberFormatException() throws Exception {
//        this.mockMvc.perform(get("/giveaways/{giveaway_id}", "abc"))
//                .andExpect(status().is(400))
//                .andExpect(content().string("Illegal Argument provided for path. Path parameter must be an integer."));
//    }
//
//    @Test
//    void test_getAllGiveawaysByValidCreatorId() throws Exception {
//        UserDTO user = new UserDTO();
//        user.setId(2);
//        user.setUsername("jane_doe");
//        user.setEmail("jane_doe@email.com");
//
//        GiveawayDTO expected = new GiveawayDTO();
//        expected.setId(7);
//        expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setIsbn("1234567890");
//        expected.setCreator(user);
//        Set<UserDTO> set = new HashSet<>();
//        expected.setEntrants(set);
//        Set<CommentDTO> comments = new LinkedHashSet<>();
//        expected.setComments(comments);
//
//        GiveawayDTO expected2 = new GiveawayDTO();
//        expected2.setId(8);
//        expected2.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected2.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected2.setIsbn("0987654321");
//        expected2.setCreator(user);
//        expected2.setEntrants(set);
//        expected2.setComments(comments);
//
//        Set<GiveawayDTO> expectedGiveaways = new HashSet<>();
//
//        expectedGiveaways.add(expected);
//        expectedGiveaways.add(expected2);
//
//        String expectedJSON = mapper.writeValueAsString(expectedGiveaways);
//        this.mockMvc.perform(get("/users/{user_id}/giveaways", 2))
//                .andExpect(status().is(200))
//                .andExpect(content().json(expectedJSON));
//    }
//
//    @Test
//    void test_getAllGiveawaysByInvalidCreatorId() throws Exception {
//        this.mockMvc.perform(get("/users/{user_id}/giveaways", "abc"))
//                .andExpect(status().is(400))
//                .andExpect(content().string("Illegal Argument provided for path. Path parameter must be an integer."));
//    }
//
//    @Test
//    void test_getAllGiveawaysByValidWinnerId() throws Exception {
//        UserDTO userCreator = new UserDTO();
//        userCreator.setId(1);
//        userCreator.setUsername("john_doe");
//        userCreator.setEmail("john_doe@email.com");
//
//        UserDTO user = new UserDTO();
//        user.setId(2);
//        user.setUsername("jane_doe");
//        user.setEmail("jane_doe@email.com");
//
//        GiveawayDTO expected = new GiveawayDTO();
//        expected.setId(4);
//        expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setIsbn("1234567890");
//        expected.setCreator(userCreator);
//        Set<UserDTO> set = new HashSet<>();
//        expected.setWinner(user);
//        expected.setEntrants(set);
//        List<CommentDTO> comments = new LinkedHashSet<>();
//        expected.setComments(comments);
//
//        GiveawayDTO expected2 = new GiveawayDTO();
//        expected2.setId(5);
//        expected2.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected2.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected2.setIsbn("0987654321");
//        expected2.setCreator(userCreator);
//        expected2.setWinner(user);
//        expected2.setEntrants(set);
//        expected2.setComments(comments);
//
//        GiveawayDTO expected3 = new GiveawayDTO();
//        expected3.setId(6);
//        expected3.setStartTime(Timestamp.valueOf("2022-04-11 00:00:00"));
//        expected3.setEndTime(Timestamp.valueOf("2022-04-11 00:00:00"));
//        expected3.setIsbn("1111111111");
//        expected3.setCreator(userCreator);
//        expected3.setWinner(user);
//        expected3.setEntrants(set);
//        expected3.setComments(comments);
//
//        Set<GiveawayDTO> expectedGiveaways = new HashSet<>();
//        expectedGiveaways.add(expected);
//        expectedGiveaways.add(expected2);
//        expectedGiveaways.add(expected3);
//
//
//        String expectedJSON = mapper.writeValueAsString(expectedGiveaways);
//        this.mockMvc.perform(get("/giveaways/winners/{user_id}", 2))
//                .andExpect(status().is(200))
//                .andExpect(content().json(expectedJSON));
//
//    }
//
//    @Test
//    void test_getAllGiveawaysByInvalidWinnerId() throws Exception {
//        this.mockMvc.perform(get("/giveaways/winners/{user_id}", "abc"))
//                .andExpect(status().is(400))
//                .andExpect(content().string("Illegal Argument provided for path. Path parameter must be an integer."));
//    }
//
//    @Test
//    void test_getAllGiveawaysInProgress() throws Exception {
//        UserDTO user2 = new UserDTO();
//        user2.setId(2);
//        user2.setUsername("jane_doe");
//        user2.setEmail("jane_doe@email.com");
//
//        UserDTO user = new UserDTO();
//        user.setId(1);
//        user.setUsername("john_doe");
//        user.setEmail("john_doe@email.com");
//
//        GiveawayDTO expected = new GiveawayDTO();
//        expected.setId(1);
//        expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected.setIsbn("1234567890");
//        expected.setCreator(user);
//        Set<UserDTO> set = new HashSet<>();
//        expected.setEntrants(set);
//        Set<CommentDTO> comments = new LinkedHashSet<>();
//        expected.setComments(comments);
//
//        GiveawayDTO expected2 = new GiveawayDTO();
//        expected2.setId(2);
//        expected2.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected2.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected2.setIsbn("0987654321");
//        expected2.setCreator(user);
//        expected2.setEntrants(set);
//        expected2.setComments(comments);
//
//        GiveawayDTO expected3 = new GiveawayDTO();
//        expected3.setId(3);
//        expected3.setStartTime(Timestamp.valueOf("2022-04-11 00:00:00"));
//        expected3.setEndTime(Timestamp.valueOf("2022-04-11 00:00:00"));
//        expected3.setIsbn("1111111111");
//        expected3.setCreator(user);
//        expected3.setEntrants(set);
//        expected3.setComments(comments);
//
//        GiveawayDTO expected4 = new GiveawayDTO();
//        expected4.setId(7);
//        expected4.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected4.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
//        expected4.setIsbn("1234567890");
//        expected4.setCreator(user2);
//        expected4.setEntrants(set);
//        expected4.setComments(comments);
//
//        GiveawayDTO expected5 = new GiveawayDTO();
//        expected5.setId(8);
//        expected5.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected5.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
//        expected5.setIsbn("0987654321");
//        expected5.setCreator(user2);
//        expected5.setEntrants(set);
//        expected5.setComments(comments);
//
//        Set<GiveawayDTO> expectedGiveaways = new HashSet<>();
//
//        expectedGiveaways.add(expected);
//        expectedGiveaways.add(expected2);
//        expectedGiveaways.add(expected3);
//        expectedGiveaways.add(expected4);
//        expectedGiveaways.add(expected5);
//
//        String expectedJSON = mapper.writeValueAsString(expectedGiveaways);
//
//        this.mockMvc.perform(get("/giveaways"))
//                .andExpect(status().is(200))
//                .andExpect(content().json(expectedJSON));
//    }
//
//}
