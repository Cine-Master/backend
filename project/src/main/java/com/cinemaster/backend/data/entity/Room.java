package com.cinemaster.backend.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Size(min = 1, max = 20, message = "The name length must be between 1 and 20")
    @Pattern(regexp = "[\\w -\\.,:;\\+_\\(\\)\\[\\]\\{\\}]+", message = "The name must follow the pattern [a-zA-Z0-9 -.,:;+()[]{}]")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "room")
    private List<Show> shows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
