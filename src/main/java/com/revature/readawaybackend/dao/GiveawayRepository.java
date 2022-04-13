package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.Giveaway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GiveawayRepository extends JpaRepository<Giveaway, Integer> {

    public abstract Set<Giveaway> findByWinnerIsNull();

    public abstract Set<Giveaway> findByCreatorId(int creatorId);

    public abstract Set<Giveaway> findByWinnerId(int winnerId);
}
