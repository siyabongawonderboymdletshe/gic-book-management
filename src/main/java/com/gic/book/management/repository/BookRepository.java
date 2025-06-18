package com.gic.book.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gic.book.management.model.Book;

/**
 * This interface extends JpaRepository to provide CRUD operations for the Book entity.
 * It includes methods to check for existence of books by title, author, and ISBN,
 * as well as methods to find books by title or author.
 */
public interface BookRepository extends JpaRepository<Book, UUID> {
  
    /**
     * Checks if a book exists by its title and author.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @return true if a book with the given title and author exists, false otherwise
     */
    boolean existsByTitleAndAuthor(String title, String author);

    /**
     * Checks if a book exists by its ISBN.
     *
     * @param isbn the ISBN of the book
     * @return true if a book with the given ISBN exists, false otherwise
     */
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable);
}
