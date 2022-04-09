package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.Giveaway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiveawayRepository extends JpaRepository<Giveaway, Integer> {
}
