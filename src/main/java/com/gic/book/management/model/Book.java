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

/**
 * This class represents a Book entity in the book management system.
 * It contains fields for the book's ID, ISBN, title, and author.
 */
@Entity
@Table(name="book")
public class Book {

    /**
     * The unique identifier for the book.
     * It is generated using UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;


    /**
     * The International Standard Book Number (ISBN) of the book.
     * It is a unique identifier for books and is required.
     */
    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title can be up to 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * The author of the book.
     * It is required and can be up to 50 characters long.
     */
    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author name can be up to 50 characters")
    @Column(nullable = false, length = 50)
    private String author;

    public Book() 
    {
    }

    public Book(String title, String author) 
    {
        this.title = title;
        this.author = author;
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