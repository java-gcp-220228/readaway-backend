package com.revature.readawaybackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "post_time", nullable = false)
    private Timestamp postTime;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JoinColumn(name = "parent_comment_id")
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<Comment> replies = new ArrayList<>();
}
