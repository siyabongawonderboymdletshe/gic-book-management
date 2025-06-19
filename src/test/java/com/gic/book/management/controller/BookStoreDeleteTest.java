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
class BookStoreDeleteTest {
    @InjectMocks
    private BookController bookStoreController;

    @Mock
    private BookService bookStoreService;

    @Test
    void deleteBook_shouldReturn204WhenBookExists() {

        UUID id = UUID.randomUUID();

        Mockito.when(bookStoreService.deleteBook(id)).thenReturn(true);

        ResponseEntity<?> response = bookStoreController.deleteBook(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteBook_shouldReturnNotFoundWhenBookIdDoesNotExist() {

        UUID id = UUID.randomUUID();

        Mockito.when(bookStoreService.deleteBook(id)).thenReturn(false);

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("message", "Book with ID " + id + " not found.");
        expectedBody.put("httpStatus", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = bookStoreController.deleteBook(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }
}
