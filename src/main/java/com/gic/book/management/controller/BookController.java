package com.gic.book.management.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gic.book.management.dto.BookRequestDto;
import com.gic.book.management.model.Book;
import com.gic.book.management.reponse.ResponseHandler;
import com.gic.book.management.service.BookService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookStoreService;

    /**
     * This controller handles all the CRUD operations for the Book entity.
     * It provides endpoints to create, read, update, and delete books,
     * as well as search for books by title or author.
     */
    public BookController(BookService bookStoreService) 
    {
        this.bookStoreService = bookStoreService;
    }

    /**
     * Retrieves all books from the book store.
     *
     * @return ResponseEntity containing a list of all books.
     */        
    @GetMapping
    public ResponseEntity<?> getBooks() 
    {
         List<Book> books = bookStoreService.getAllBooks();

         String message = books.isEmpty()
        ? "No books found"
        : "Books retrieved successfully";

        return ResponseHandler.success(message, books);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id the UUID of the book to retrieve.
     * @return ResponseEntity containing the book if found, or a not found message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable UUID id) 
    {
        Book book = bookStoreService.getBookById(id);

        if (book != null) {
            return ResponseHandler.success("Book retrieved successfully", book);
        }

        return ResponseHandler.notFound("Book with ID " + id + " not found.");
    }

    /**
     * Adds a new book to the book store.
     *
     * @param book the Book object to add.
     * @param result BindingResult to capture validation errors.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookRequestDto bookDto) 
    {
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor());
        Book createdBook = bookStoreService.addBook(book);

        if (createdBook == null) {
            return ResponseHandler.conflict("Book with the same title and author already exists.");
        }

        return ResponseHandler.created("Book created successfully", createdBook);
    }

    /**
     * Updates an existing book in the book store.
     *
     * @param id the UUID of the book to update.
     * @param book the Book object containing updated information.
     * @param result BindingResult to capture validation errors.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable UUID id, @Valid @RequestBody BookRequestDto bookDto) 
    {
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor());
        Book updatedBook = bookStoreService.updateBook(id, book);

        if (updatedBook == null) {
            return ResponseHandler.notFound("Book with ID " + id + " not found.");
        }

       return ResponseHandler.success("Book updated successfully", updatedBook);
    }
    
    /**
     * Deletes a book from the book store by its ID.
     *
     * @param id the UUID of the book to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id) 
    {
        boolean deleted = bookStoreService.deleteBook(id);

        if (deleted) {
           return ResponseHandler.success("Book deleted successfully", null);
        }

         return ResponseHandler.notFound("Book with ID " + id + " not found.");
    }

    /**
     * Searches for books by title or author.
     *
     * @param queryText the text to search for in book titles or authors.
     * @param page the page number for pagination.
     * @param size the number of books per page.
     * @param sortBy the field to sort by (default is "title").
     * @param sortDir the direction of sorting (default is "asc").
     * @return ResponseEntity containing a list of books matching the search criteria.
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam String queryText,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "title") String sortBy,
    @RequestParam(defaultValue = "asc") String sortDir) 
    {
        List<Book> books = bookStoreService.searchByTitleOrAuthor(queryText, page, size, sortBy, sortDir);

        String message = books.isEmpty()
            ? "No books found matching the query: " + queryText
            : "Search completed";

        return ResponseHandler.success(message, books);
    }
}