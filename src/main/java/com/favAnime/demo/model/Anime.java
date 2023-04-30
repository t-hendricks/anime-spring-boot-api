package com.favAnime.demo.model;

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

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
