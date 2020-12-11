package com.cinemaster.backend.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "mocc")
public class Show {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Size(min = 1, max = 20, message = "The name length must be between 1 and 20")
    @Pattern(regexp = "[\\w -\\.,:;\\+_\\(\\)\\[\\]\\{\\}]+", message = "The name must follow the pattern [a-zA-Z0-9 -.,:;+()[]{}]")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 7, max = 255, message = "The url length must be between 7 and 255")
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)", message = "The url is not valid")
    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Size(min = 1, max = 50, message = "The language length must be between 7 and 255")
    @Pattern(regexp = "[\\w ]+", message = "The language must be composed only by letters, numbers and spaces")
    @Column(name = "language")
    private String language;

    @Size(min = 1, max = 50, message = "The location length must be between 7 and 255")
    @Pattern(regexp = "[\\w ]+", message = "The location must be composed only by letters, numbers and spaces")
    @Column(name = "production_location")
    private String production_location;

    @Size(min = 1, max = 100, message = "The language length must be between 7 and 255")
    @Pattern(regexp = "[\\w -\\.,:;\\+_\\(\\)\\[\\]\\{\\}]+", message = "The description must follow the pattern [a-zA-Z0-9 -.,:;+()[]{}]")
    @Column(name = "description")
    private String description;

    @Column(name = "coming_soon", nullable = false)
    private Boolean comingSoon;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "show_categories",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "show_directors",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id")
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
            name = "show_actors",
            joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id")
    )
    private List<Actor> actors;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProduction_location() {
        return production_location;
    }

    public void setProduction_location(String production_location) {
        this.production_location = production_location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComingSoon() {
        return comingSoon;
    }

    public void setComingSoon(Boolean comingSoon) {
        this.comingSoon = comingSoon;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
