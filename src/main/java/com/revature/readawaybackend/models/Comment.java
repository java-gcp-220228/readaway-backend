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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "post_time", nullable = false)
  private Timestamp postTime;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @JoinColumn(name = "parent_comment_id")
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @OrderBy("post_time")
  private Set<Comment> replies = new LinkedHashSet<>();
}
