package com.gic.book.management.service;

import java.util.List;
import java.util.UUID;

import com.gic.book.management.model.Book;

/**
 * This interface defines the contract for the BookStoreService,
 * which provides methods for managing books in a bookstore application.
 * It includes methods for adding, updating, deleting, retrieving,
 * and searching for books.
 */
public interface BookStoreService {
    /**
     * Adds a new book to the bookstore.
     *
     * @param book The book to be added.
     * @return The added book, or null if a book with the same title and author already exists.
     */
    public Book addBook(Book book);

    /**
     * Updates an existing book in the bookstore.
     *
     * @param id   The ID of the book to be updated.
     * @param book The book object containing updated information.
     * @return The updated book, or a new book if the ID does not exist.
     */
    public Book updateBook(UUID id, Book book);

    /**
     * Deletes a book from the bookstore.
     *
     * @param bookId The ID of the book to be deleted.
     * @return true if the book was successfully deleted, false otherwise.
     */
    public boolean deleteBook(UUID bookId);

    /**
     * Retrieves a book by its ID.
     *
     * @param bookId The ID of the book to be retrieved.
     * @return The book with the specified ID, or null if it does not exist.
     */
    public Book getBookById(UUID bookId);

    /**
     * Retrieves all books in the bookstore.
     *
     * @return A list of all books.
     */
    public List<Book> getAllBooks();

    /**
     * Searches for books by title or author, with pagination and sorting options.
     *
     * @param queryText The text to search for in book titles or authors.
     * @param page      The page number for pagination.
     * @param size      The number of books per page.
     * @param sortBy    The field to sort by (e.g., "title", "author").
     * @param sortDir   The direction of sorting ("asc" or "desc").
     * @return A list of books matching the search criteria.
     */
    public List<Book> searchByTitleOrAuthor(String queryText, int page, int size, String sortBy, String sortDir);
}