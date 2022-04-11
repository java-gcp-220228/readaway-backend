package com.revature.models;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="giveaways")
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

    @Column(name = "active", nullable = false)
    private boolean active;

    @JoinColumn(name = "creator_id")
    @ManyToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User creator;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Entries",
            joinColumns = { @JoinColumn(name = "giveaway_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> users = new HashSet<>();

    public Giveaway() {
    }

    public Giveaway(Timestamp startTime, Timestamp endTime, String isbn, boolean active, User creator) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isbn = isbn;
        this.active = active;
        this.creator = creator;
    }

    public Giveaway(int id, Timestamp startTime, Timestamp endTime, String isbn, boolean active, User creator, Set<User> users) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isbn = isbn;
        this.active = active;
        this.creator = creator;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giveaway giveaway = (Giveaway) o;
        return id == giveaway.id && active == giveaway.active && Objects.equals(startTime, giveaway.startTime) && Objects.equals(endTime, giveaway.endTime) && Objects.equals(isbn, giveaway.isbn) && Objects.equals(creator, giveaway.creator) && Objects.equals(users, giveaway.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, isbn, active, creator, users);
    }

    @Override
    public String toString() {
        return "Giveaway{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isbn='" + isbn + '\'' +
                ", active=" + active +
                ", creator=" + creator +
                ", users=" + users +
                '}';
    }
}
