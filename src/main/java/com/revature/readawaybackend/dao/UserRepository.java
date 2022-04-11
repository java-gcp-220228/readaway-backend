package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
