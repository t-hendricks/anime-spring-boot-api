package com.favAnime.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity // JPA entity
@Table(name = "genres") // table name in DB
public class Genre {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates PK (id)
    private Long id;

    @Column
    private String name;

    // one genre can contain more than one anime
    @OneToMany(mappedBy = "genre", orphanRemoval = true) // genre owns the bidirectional relationship
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Anime> anime;

    // many genres belong to a one user
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    // getter
    public List<Anime> getAnime() {
        return anime;
    }

    // setter
    public void setAnime(List<Anime> anime) {
        this.anime = anime;
    }

    // getter
    public User getUser() {
        return user;
    }

    // setter
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
