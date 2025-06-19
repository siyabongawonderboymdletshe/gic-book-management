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
class BookStorePutTest {
    @InjectMocks
    private BookController bookStoreController;

    @Mock
    private BookService bookStoreService;

    @Test
    void updateBook_shouldReturnUpdatedBookIfIdExists() {
        UUID id = UUID.randomUUID();
        BookRequestDto dTo = new BookRequestDto("Valid Title", "Valid Author");
        Book updated = new Book(dTo.getTitle(), dTo.getAuthor());

        Mockito.when(bookStoreService.updateBook(Mockito.any(UUID.class), Mockito.any(Book.class)))
           .thenReturn(updated);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book updated successfully");
        expectedResponse.put("httpStatus", HttpStatus.OK);
        expectedResponse.put("data", updated);

        ResponseEntity<?> response = bookStoreController.updateBook(id, dTo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());        
    }

     @Test
    void updateBook_shouldReturnNotFoundErrorIfIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        BookRequestDto dTo = new BookRequestDto("Valid Title", "Valid Author");
       
        Mockito.when(bookStoreService.updateBook(Mockito.any(UUID.class), Mockito.any(Book.class)))
           .thenReturn(null);

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("message", "Book with ID " + id + " not found.");
        expectedBody.put("httpStatus", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = bookStoreController.updateBook(id, dTo);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedBody, response.getBody());
    }
}
