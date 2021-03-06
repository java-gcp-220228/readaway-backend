package com.revature.readawaybackend.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "giveaways")
@Data
@NoArgsConstructor
public class Giveaway {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "start_time", nullable = false)
  private Timestamp startTime;

  @Column(name = "end_time", nullable = false)
  private Timestamp endTime;

  @Column(name = "isbn", nullable = false)
  private String isbn;

  @JoinColumn(name = "creator_id", nullable = false)
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User creator;

  @JoinColumn(name = "winner_id")
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User winner;

  @JoinColumn(name = "giveaway_id")
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @OrderBy("post_time")
  private Set<Comment> comments = new LinkedHashSet<>();

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinTable(
      name = "Entries",
      joinColumns = {@JoinColumn(name = "giveaway_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  private Set<User> entrants = new HashSet<>();

}
