package com.revature.readawaybackend.dao;

import com.revature.readawaybackend.models.Giveaway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface GiveawayRepository extends JpaRepository<Giveaway, Integer> {
    // winner null
    // creator id
    // winner = id
    public abstract List<Giveaway> findByWinnerIdIsNull();

    public abstract List<Giveaway> findByCreatorId(int creatorId);

    public abstract List<Giveaway> findByWinnerId(int winnerId);
}
