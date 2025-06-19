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
import com.gic.book.management.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerDeleteBookTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void deleteBook_returnsOk_whenBookExists() {
        UUID id = UUID.randomUUID();
        Mockito.when(bookService.deleteBook(id)).thenReturn(true);

        ResponseEntity<?> response = bookController.deleteBook(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteBook_returnsNotFound_whenBookDoesNotExist() {
        UUID id = UUID.randomUUID();
        Mockito.when(bookService.deleteBook(id)).thenReturn(false);

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("message", "Book with ID " + id + " not found.");
        expectedBody.put("httpStatus", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = bookController.deleteBook(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }
}
