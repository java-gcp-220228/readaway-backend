package com.revature.readawaybackend.service;

import com.revature.readawaybackend.dao.GiveawayRepository;
import com.revature.readawaybackend.models.Comment;
import com.revature.readawaybackend.models.Giveaway;
import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GiveawayServiceTest {

  @Mock
  private GiveawayRepository giveawayRepository;

  @InjectMocks
  private GiveawayService giveawayService;

  @Test
  void getGiveawayByValidId() {

    User user = new User();
    user.setId(1);
    user.setUsername("john_doe");
    user.setPassword("pass");
    user.setEmail("john_doe@email.com");

    Giveaway expected = new Giveaway();
    expected.setId(1);
    expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setIsbn("1234567890");
    expected.setCreator(user);

    when(giveawayRepository.findById(1)).thenReturn(Optional.of(expected));

    Giveaway actual = giveawayService.getGiveawayById("1");
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void getGiveawayById_NonExistent() {
    when(giveawayRepository.findById(500)).thenReturn(Optional.empty());

    Assertions.assertThrows(HttpClientErrorException.class, () -> {
      giveawayService.getGiveawayById("500");
    });
  }

  @Test
  void getGiveawayByInvalidId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      giveawayService.getGiveawayById("abc");
    });
  }

  @Test
  void getAllGiveawaysByValidCreatorId() {
    User user = new User();
    user.setId(1);
    user.setUsername("jane_doe");
    user.setPassword("123");
    user.setEmail("jane_doe@email.com");

    Giveaway expected = new Giveaway();
    expected.setId(1);
    expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setIsbn("1234567890");
    expected.setCreator(user);

    Giveaway expected2 = new Giveaway();
    expected2.setId(2);
    expected2.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
    expected2.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
    expected2.setIsbn("0987654321");
    expected2.setCreator(user);

    Set<Giveaway> expectedGiveaways = new HashSet<>();

    expectedGiveaways.add(expected);
    expectedGiveaways.add(expected2);

    when(giveawayRepository.findByCreatorId(1)).thenReturn(expectedGiveaways);

    Set<Giveaway> actualGiveaways = giveawayService.getAllGiveawaysByCreatorId("1");

    Assertions.assertEquals(expectedGiveaways, actualGiveaways);
  }

  @Test
  void getAllGiveawaysByInvalidCreatorId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      giveawayService.getGiveawayById("abc");
    });
  }

  @Test
  void getAllGiveawaysByWinnerId() {
    User user = new User();
    user.setId(1);
    user.setUsername("jane_doe");
    user.setPassword("123");
    user.setEmail("jane_doe@email.com");

    Giveaway expected = new Giveaway();
    expected.setId(1);
    expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setIsbn("1234567890");
    expected.setCreator(user);

    Giveaway expected2 = new Giveaway();
    expected2.setId(2);
    expected2.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
    expected2.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
    expected2.setIsbn("0987654321");
    expected2.setCreator(user);

    Set<Giveaway> expectedGiveaways = new HashSet<>();

    expectedGiveaways.add(expected);
    expectedGiveaways.add(expected2);

    when(giveawayRepository.findByWinnerId(1)).thenReturn(expectedGiveaways);

    Set<Giveaway> actualGiveaways = giveawayService.getAllGiveawaysByWinnerId("1");

    Assertions.assertEquals(expectedGiveaways, actualGiveaways);
  }

  @Test
  void getAllGiveawaysByInvalidWinnerId() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      giveawayService.getAllGiveawaysByWinnerId("abc");
    });
  }

  @Test
  void getAllGiveawaysInProgress() {
    User user = new User();
    user.setId(1);
    user.setUsername("jane_doe");
    user.setPassword("123");
    user.setEmail("jane_doe@email.com");

    Giveaway expected = new Giveaway();
    expected.setId(1);
    expected.setStartTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setEndTime(Timestamp.valueOf("2022-04-09 00:00:00"));
    expected.setIsbn("1234567890");
    expected.setCreator(user);

    Giveaway expected2 = new Giveaway();
    expected2.setId(2);
    expected2.setStartTime(Timestamp.valueOf("2022-04-10 00:00:00"));
    expected2.setEndTime(Timestamp.valueOf("2022-04-10 00:00:00"));
    expected2.setIsbn("0987654321");
    expected2.setCreator(user);

    Set<Giveaway> expectedGiveaways = new HashSet<>();

    expectedGiveaways.add(expected);
    expectedGiveaways.add(expected2);

    when(giveawayRepository.findByWinnerIsNull()).thenReturn(expectedGiveaways);

    Set<Giveaway> actualGiveaways = giveawayService.getAllGiveawaysInProgress();

    Assertions.assertEquals(expectedGiveaways, actualGiveaways);

  }

  @Test
  void addNewGiveaway() {
    Giveaway newGiveaway = new Giveaway();
    newGiveaway.setIsbn("12234325234");

    Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 10000);

    newGiveaway.setEndTime(timestamp);
    Set<User> entrants = new HashSet<>();
    newGiveaway.setEntrants(entrants);
    when(giveawayRepository.save(newGiveaway)).thenReturn(newGiveaway);

    Assertions.assertDoesNotThrow(() -> giveawayService.addNewGiveaway(newGiveaway));
  }

  @Test
  void addNewGiveawayTrimIsbn() {
    Giveaway newGiveaway = new Giveaway();
    newGiveaway.setIsbn("            2359082309580923850932");

    Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 10000);

    newGiveaway.setEndTime(timestamp);
    Set<User> entrants = new HashSet<>();
    newGiveaway.setEntrants(entrants);
    when(giveawayRepository.save(newGiveaway)).thenReturn(newGiveaway);

    Assertions.assertDoesNotThrow(() -> giveawayService.addNewGiveaway(newGiveaway));
  }

  @Test
  void updateGiveawayWinner() throws InterruptedException {
    Giveaway newGiveaway = new Giveaway();
    newGiveaway.setIsbn("29305902895");
    Timestamp endTime = new Timestamp(System.currentTimeMillis() + 1000);
    newGiveaway.setEndTime(endTime);
    newGiveaway.setId(1);
    Set<User> entries = new HashSet<>();

    User user = new User();
    user.setId(1);
    user.setUsername("jane_doe");
    user.setPassword("123");
    user.setEmail("jane_doe@email.com");

    entries.add(user);
    newGiveaway.setEntrants(entries);
    newGiveaway.setCreator(user);

    when(giveawayRepository.findById(1)).thenReturn(Optional.of(newGiveaway));

    giveawayService.addNewGiveaway(newGiveaway);

    Thread.sleep(2000);

    Assertions.assertEquals(user, newGiveaway.getWinner());
  }

  @Test
  void updateGiveawayWinnerNoEntries() throws InterruptedException {

    Giveaway giveaway = new Giveaway();
    giveaway.setIsbn("18478917548931");
    Timestamp endTime = new Timestamp(System.currentTimeMillis() + 1000);
    giveaway.setEndTime(endTime);
    giveaway.setEntrants(new HashSet<>());
    giveaway.setId(1);

    when(giveawayRepository.findById(1)).thenReturn(Optional.of(giveaway));

    giveawayService.addNewGiveaway(giveaway);

    Thread.sleep(2000);

    verify(giveawayRepository, times(1)).delete(giveaway);
  }

  @Test
  void testServerRestartPickWinnersFromEndedGiveaways() {
    Giveaway giveaway = new Giveaway();
    giveaway.setId(1);
    giveaway.setIsbn("14109385901385");
    Timestamp endTime = new Timestamp(System.currentTimeMillis() - 10000);
    giveaway.setEndTime(endTime);
    Set<User> entrants = new HashSet<>();

    User user = new User();
    user.setId(1);
    user.setUsername("jane_doe");
    user.setPassword("123");
    user.setEmail("jane_doe@email.com");
    entrants.add(user);
    giveaway.setEntrants(entrants);
    giveaway.setCreator(user);

    Set<Giveaway> giveaways = new HashSet<>();
    giveaways.add(giveaway);
    giveaway.setEntrants(entrants);
    when(giveawayRepository.findByWinnerIsNull()).thenReturn(giveaways);
    when(giveawayRepository.findById(1)).thenReturn(Optional.of(giveaway));
    giveawayService.serverRestartScheduleGiveaways();
    Assertions.assertEquals(user, giveaway.getWinner());
  }

  @Test
  void test_ServerRestartScheduleOngoingGiveaways() throws InterruptedException {
    Giveaway giveaway = new Giveaway();
    giveaway.setId(1);
    giveaway.setIsbn("14109385901385");
    Timestamp endTime = new Timestamp(System.currentTimeMillis() + 1000);
    giveaway.setEndTime(endTime);
    Set<User> entrants = new HashSet<>();

    User user = new User();
    user.setId(1);
    user.setUsername("jane_doe");
    user.setPassword("123");
    user.setEmail("jane_doe@email.com");
    entrants.add(user);
    giveaway.setEntrants(entrants);
    giveaway.setCreator(user);

    Set<Giveaway> giveaways = new HashSet<>();
    giveaways.add(giveaway);
    giveaway.setEntrants(entrants);
    when(giveawayRepository.findByWinnerIsNull()).thenReturn(giveaways);
    when(giveawayRepository.findById(1)).thenReturn(Optional.of(giveaway));
    giveawayService.serverRestartScheduleGiveaways();

    Thread.sleep(2000);
    Assertions.assertEquals(user, giveaway.getWinner());
  }

  @Test
  void addCommentToGiveaway() {
    Giveaway giveaway = new Giveaway();
    Comment comment = new Comment();

    when(giveawayRepository.findById(1)).thenReturn(Optional.of(giveaway));

    Assertions.assertDoesNotThrow(() -> giveawayService.addCommentToGiveaway("1", comment));
  }
}
