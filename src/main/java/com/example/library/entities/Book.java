package com.example.library.entities;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String isbn;
    private String name;
    private String author;
    private String plot;
    private String genre;
    private Boolean is_available;

    public Book () {}

    public Book (Integer id, String isbn, String name, String author, String plot, String genre, Boolean is_available) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.plot = plot;
        this.genre = genre;
        this.is_available = is_available;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return  this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlot() {
        return this.plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getIs_available() {
        return this.is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

}
