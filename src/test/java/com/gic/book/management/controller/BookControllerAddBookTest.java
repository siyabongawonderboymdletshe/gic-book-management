package com.gic.book.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gic.book.management.dto.BookRequestDto;
import com.gic.book.management.model.Book;
import com.gic.book.management.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerAddBookTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void addBook_returnsCreatedBook_whenBookDoesNotExist() {
        BookRequestDto dto = new BookRequestDto("Valid Title", "Valid Author");
        Book added = new Book(dto.getTitle(), dto.getAuthor());

        Mockito.when(bookService.addBook(Mockito.any(Book.class)))
               .thenReturn(added);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book added successfully");
        expectedResponse.put("httpStatus", HttpStatus.CREATED);
        expectedResponse.put("data", added);

        ResponseEntity<?> response = bookController.addBook(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void addBook_returnsConflict_whenBookAlreadyExists() {
        BookRequestDto dto = new BookRequestDto("Valid Title", "Valid Author");

        Mockito.when(bookService.addBook(Mockito.any(Book.class)))
               .thenReturn(null);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book with the same title and author already exists.");
        expectedResponse.put("httpStatus", HttpStatus.CONFLICT);

        ResponseEntity<?> response = bookController.addBook(dto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
