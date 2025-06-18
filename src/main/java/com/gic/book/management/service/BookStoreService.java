package com.gic.book.management.service;

import java.util.List;
import java.util.UUID;

import com.gic.book.management.model.Book;

public interface BookStoreService {
    public Book addBook(Book book);
    public Book updateBook(UUID id, Book book);
    public boolean deleteBook(UUID bookId);
    public Book getBookById(UUID bookId);
    public List<Book> getAllBooks();
    public List<Book> searchByTitleOrAuthor(String queryText, int page, int size, String sortBy, String sortDir);
}