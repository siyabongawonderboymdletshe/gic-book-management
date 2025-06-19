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

import com.gic.book.management.dto.BookRequestDto;
import com.gic.book.management.service.BookService;
import com.gic.book.management.model.Book;

@ExtendWith(MockitoExtension.class)
class BookControllerUpdateBookTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void updateBook_returnsUpdatedBook_whenIdExists() {
        UUID id = UUID.randomUUID();
        BookRequestDto dto = new BookRequestDto("Valid Title", "Valid Author");
        Book updated = new Book(dto.getTitle(), dto.getAuthor());

        Mockito.when(bookService.updateBook(Mockito.any(UUID.class), Mockito.any(Book.class)))
               .thenReturn(updated);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book updated successfully");
        expectedResponse.put("httpStatus", HttpStatus.OK);
        expectedResponse.put("data", updated);

        ResponseEntity<?> response = bookController.updateBook(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void updateBook_returnsNotFound_whenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        BookRequestDto dto = new BookRequestDto("Valid Title", "Valid Author");

        Mockito.when(bookService.updateBook(Mockito.any(UUID.class), Mockito.any(Book.class)))
               .thenReturn(null);

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("message", "Book with ID " + id + " not found.");
        expectedBody.put("httpStatus", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = bookController.updateBook(id, dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }
}
