package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.Giveaway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiveawayRepository extends JpaRepository<Giveaway, Integer> {
}
