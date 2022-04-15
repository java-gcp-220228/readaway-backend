package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public abstract User findByUsernameAndPassword(String username, String password);

  public abstract User findByEmail(String email);

  public abstract User findByUsername(String username);
}