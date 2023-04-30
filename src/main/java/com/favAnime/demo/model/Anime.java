package com.favAnime.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity // JPA entity
@Table(name = "animes") // table name in DB
public class Anime {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates PK (id)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    // many animes exist only in one genre
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id") // the owner of the relationship; FK (id)
    private Genre genre;

    // required for the JPA
    public Anime() {
    }

    public Anime(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // getter
    public Long getId() {
        return id;
    }

    // setter
    public void setId(Long id) {
        this.id = id;
    }

    // getter
    public String getName() {
        return name;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    // getter
    public String getDescription() {
        return description;
    }

    // setter
    public void setDescription(String description) {
        this.description = description;
    }

    // getter
    public Genre getGenre() {
        return genre;
    }

    // setter
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
