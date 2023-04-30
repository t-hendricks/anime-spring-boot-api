package com.favAnime.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity // JPA entity
@Table(name = "users") // table name in DB
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates PK (id)
    private Long id;

    @Column(unique = true)
    private String emailAddress; // checks and makes every email unique

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // password cannot be read
    private String password;

    // user can have more than one genre
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Genre> genreList;

    // user can have more than one anime
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Anime> animeList;

    // required for JPA
    public User() {
    }

    public User(Long id, String emailAddress, String password, List<Genre> genreList, List<Anime> animeList) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
        this.genreList = genreList;
        this.animeList = animeList;
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
    public String getEmailAddress() {
        return emailAddress;
    }

    // setter
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // getter
    public String getPassword() {
        return password;
    }

    // setter
    public void setPassword(String password) {
        this.password = password;
    }

    // getter
    public List<Genre> getGenreList() {
        return genreList;
    }

    // setter
    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    // getter
    public List<Anime> getAnimeList() {
        return animeList;
    }

    // setter
    public void setAnimeList(List<Anime> animeList) {
        this.animeList = animeList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
