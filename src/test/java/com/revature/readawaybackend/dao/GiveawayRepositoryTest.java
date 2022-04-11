package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.Giveaway;
import com.revature.readawaybackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GiveawayRepositoryTest {

  @Autowired
  private GiveawayRepository giveawayRepo;

  @Test
  public void test_getGiveawayById_validGiveawayId() {

    Giveaway actual = giveawayRepo.findById(1).get();
    System.out.println(actual.getId());


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


    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_getGiveawayById_invalidId() {
    Assertions.assertFalse(giveawayRepo.findById(500).isPresent());
  }

  @Test
  public void test_getAllGiveaways() {

    List<Giveaway> actual = giveawayRepo.findAll();

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

    Assertions.assertEquals(3, actual.size());
    Assertions.assertEquals(expected, actual.get(0));
  }

  @Test
  public void test_AddGiveaway() {
    User user = new User();
    user.setId(1);
    user.setUsername("john_doe");
    user.setPassword("pass");
    user.setEmail("john_doe@email.com");

    Giveaway toAdd = new Giveaway();
    toAdd.setStartTime(Timestamp.valueOf("2022-04-12 00:00:00"));
    toAdd.setEndTime(Timestamp.valueOf("2022-04-12 00:00:00"));
    toAdd.setIsbn("0000000000");
    toAdd.setCreator(user);

    giveawayRepo.save(toAdd);

    Giveaway actual = giveawayRepo.findById(4).get();
    Giveaway expected = toAdd;
    expected.setId(4);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_UpdateGiveawayWinner_ExistingGiveaway() {
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


    expected.setWinner(user);

    Giveaway giveaway = giveawayRepo.findById(1).get();
    giveaway.setWinner(user);
    Giveaway actual = giveawayRepo.save(giveaway);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test_UpdateEntry_AddNewEntry() {
    Giveaway giveaway = giveawayRepo.findById(1).get();

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


    User user2 = new User();
    user2.setId(2);
    user2.setUsername("jane_doe");
    user2.setPassword("123");
    user2.setEmail("jane_doe@email.com");

    Set<User> entrant = new HashSet<>();
    entrant.add(user);
    entrant.add(user2);

    expected.setEntrants(entrant);

    giveaway.setEntrants(entrant);
    Giveaway actual = giveawayRepo.save(giveaway);

    Assertions.assertEquals(expected, actual);

  }

}
