package com.gic.book.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequestDto {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title can be up to 100 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author name can be up to 50 characters")
    private String author;

    public BookRequestDto(String title, String author) 
    {
        this.title = title;
        this.author = author;
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
