package com.gic.book.management.dto;

import java.util.UUID;

public class BookResponseDto {
    private UUID id;
    private String title;
    private String author;
    private String isbn;

    public BookResponseDto(UUID id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}
