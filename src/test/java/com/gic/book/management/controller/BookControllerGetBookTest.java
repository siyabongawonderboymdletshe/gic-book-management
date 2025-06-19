package com.gic.book.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gic.book.management.model.Book;
import com.gic.book.management.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerGetBookTest {
   @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void getBookById_returnsBookDetails_whenBookExists() {
        Book book = new Book("Valid Title", "Valid Author");
        UUID id = book.getId();

        Mockito.when(bookService.getBookById(id)).thenReturn(book);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book retrieved successfully");
        expectedResponse.put("httpStatus", HttpStatus.OK);
        expectedResponse.put("data", book);

        ResponseEntity<?> response = bookController.getBookById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getBookById_returnsNotFound_whenBookDoesNotExist() {
        UUID id = UUID.randomUUID();

        Mockito.when(bookService.getBookById(id)).thenReturn(null);

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("message", "Book with ID " + id + " not found.");
        expectedBody.put("httpStatus", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = bookController.getBookById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }
}
