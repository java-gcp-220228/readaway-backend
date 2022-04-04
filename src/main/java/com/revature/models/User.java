package com.revature.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @ManyToMany(mappedBy = "giveaways")
    private Set<Giveaway> entries = new HashSet<>();

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int id, String username, String password, String email, Set<Giveaway> entries) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.entries = entries;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id=id;}

    public String getUsername(){return username;}

    public  void setUsername(String username){this.username = username;}

    public String getPassword(){return username;}

    public  void setPassword(String username){this.password = password;}

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setEntries(Set<Giveaway> entries) { this.entries = entries; }

    public Set<Giveaway> getEntries() { return entries; }




    @Override
    public int hashCode() {return Objects.hash(id, username, password, email, entries);}

    @Override
    public boolean equals(Object o) {
        if(this == o)return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(entries, user.entries);
    }

}
