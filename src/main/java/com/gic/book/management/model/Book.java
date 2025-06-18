package com.gic.book.management.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

     @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title can be up to 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author name can be up to 50 characters")
    @Column(nullable = false, length = 50)
    private String author;

    public Book() 
    {
    }

    public Book(String title, String author, String isbn) 
    {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
       
    }

    public UUID getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}