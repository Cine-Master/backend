package com.cinemaster.backend.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    //    @Size(min = 6, max = 32, message = "The name length must be between 6 and 32")
//    @Pattern(regexp = "[a-zA-Z0-9]+", message = "The name must follow the pattern [a-zA-Z0-9]")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
