package com.gic.book.management.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.gic.book.management.model.Book;
import com.gic.book.management.reponse.ResponseHandler;
import com.gic.book.management.service.BookStoreService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) 
    {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() 
    {
        return ResponseEntity.ok(bookStoreService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable UUID id) 
    {
        Book book = bookStoreService.getBookById(id);

        if (book != null) {
            return ResponseHandler.success("", book);
        }

        return ResponseHandler.notFound("Book with ID " + id + " not found.");
    }

    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, BindingResult result) 
    {
        if (result.hasErrors()) {
            return ResponseHandler.validationErrorBuilder(result);
        }

        Book createdBook = bookStoreService.addBook(book);

        if (createdBook == null) {
            return ResponseHandler.conflict("Book with the same title and author already exists.");
        }

        return ResponseHandler.created("Book created successfully", createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@Valid @PathVariable UUID id, @RequestBody Book book, BindingResult result) 
    {
        if (result.hasErrors()) {
            return ResponseHandler.validationErrorBuilder(result);
        }

        Book updatedBook = bookStoreService.updateBook(id, book);

        if (updatedBook == null) {
            return ResponseHandler.notFound("Book with ID " + id + " not found.");
        }

       return ResponseHandler.success("Book updated successfully", updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id) 
    {
        boolean deleted = bookStoreService.deleteBook(id);

        if (deleted) {
           return ResponseHandler.success("Book deleted successfully", null);
        }

         return ResponseHandler.notFound("Book with ID " + id + " not found.");
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookById(@RequestParam String queryText,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "title") String sortBy,
    @RequestParam(defaultValue = "asc") String sortDir) 
    {
        List<Book> books = bookStoreService.searchByTitleOrAuthor(queryText, page, size, sortBy, sortDir);

        if (books != null && !books.isEmpty()) {
            return ResponseHandler.success("", books);
        }

        return ResponseHandler.notFound("No books found matching the query: " + queryText);
    }
}