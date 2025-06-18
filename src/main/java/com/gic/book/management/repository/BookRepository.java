package com.gic.book.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gic.book.management.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    // Checks if a book exists with the same title and author
    boolean existsByTitleAndAuthor(String title, String author);

     // Checks if a book exists with the same title
    boolean existsByTitle(String title);

    // Checks if a book exists with the same author
    boolean existsByAuthor(String author);

    // checks by ISBN
    boolean existsByIsbn(String isbn);

    Book findByTitleAndAuthor(String title, String author);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    Book findByIsbn(String isbn);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable);
}
