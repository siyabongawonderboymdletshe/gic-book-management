package com.gic.book.management.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gic.book.management.helper.IsbnGeneratorHelper;
import com.gic.book.management.model.Book;
import com.gic.book.management.repository.BookRepository;
import com.gic.book.management.service.BookStoreService;

@Service
public class BookStoreServiceImpl implements BookStoreService {

   BookRepository bookRepository;
 
    public BookStoreServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }    

    /**
     * Adds a new book to the repository if it does not already exist.
     * 
     * @param book The book to be added.
     * @return The added book, or null if a book with the same title and author already exists.
     */
    @Override
    public Book addBook(Book book) 
    {

        if (bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
            return null;
        }
    
        Book newBook = createBook(book.getTitle(), book.getAuthor());
        bookRepository.save(newBook);
        return newBook;
    }

    /**
     * Updates an existing book in the repository.
     *
     * @param id   The ID of the book to be updated.
     * @param book The book object containing updated information.
     * @return The updated book, or a new book if the ID does not exist.
     */

    @Override
    public Book updateBook(UUID id, Book book) 
    {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) 
        {
         return addBook(book);
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        bookRepository.save(existingBook);
        return existingBook;
    }

    /**
     * Deletes a book from the repository by its ID.
     *
     * @param bookId The ID of the book to be deleted.
     * @return true if the book was deleted, false if it did not exist.
     */
    @Override
    public boolean deleteBook(UUID bookId) 
    {
        if (!bookRepository.existsById(bookId)) {
            return false;
        }

        bookRepository.deleteById(bookId);
        return true;
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param bookId The ID of the book to be retrieved.
     * @return The book if found, or null if not found.
     */
    @Override
    public Book getBookById(UUID bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    /**
     * Retrieves all books from the repository.
     *
     * @return A list of all books.
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Searches for books by title or author, with pagination and sorting.
     *
     * @param queryText The text to search for in titles or authors.
     * @param page      The page number to retrieve.
     * @param size      The number of items per page.
     * @param sortBy    The field to sort by.
     * @param sortDir   The direction of sorting (asc or desc).
     * @return A list of books matching the search criteria.
     */
    @Override
    public List<Book> searchByTitleOrAuthor(String queryText, int page, int size, String sortBy, String sortDir)
    {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(queryText, queryText, getPageable(page, size, sortBy, sortDir))
                .stream()
                .toList();
    }

    private Pageable getPageable(int page, int size, String sortBy, String sortDir) 
    {
        if (sortBy == null || sortBy.isBlank()) {
            return PageRequest.of(page, size, Sort.by("id").ascending());
        }

        Sort sort = sortDir.equalsIgnoreCase("desc") 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }

    private Book createBook(String title, String author) 
    {
        return new Book(title, author, IsbnGeneratorHelper.generateIsbn13());
    }
    
}
