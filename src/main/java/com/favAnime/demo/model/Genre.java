package com.favAnime.demo.model;

import javax.persistence.*;

@Entity // JPA entity
@Table(name = "genres") // table name in DB
public class Genre {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates PK (id)
    private Long id;

    @Column
    private String name;

    // required for the JPA
    public Genre() {
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
