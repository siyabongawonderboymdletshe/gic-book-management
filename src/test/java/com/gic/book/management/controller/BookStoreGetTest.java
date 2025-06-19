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

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookStoreGetTest {
   @InjectMocks
    private BookController bookStoreController;

    @Mock
    private BookService bookStoreService;

    @Test
    void getBook_shouldReturnBookDetailsWhenBookExists() {

        Book book = new Book("Valid Title", "Valid Author");
        UUID id = book.getId();

        Mockito.when(bookStoreService.getBookById(id)).thenReturn(book);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "");
        expectedResponse.put("httpStatus", HttpStatus.OK);
        expectedResponse.put("data", book);

        ResponseEntity<?> response = bookStoreController.getBookById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getBook_shouldReturnNotFoundWhenBookIdDoesNotExist() {

        UUID id = UUID.randomUUID();

        Mockito.when(bookStoreService.getBookById(id)).thenReturn(null);

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("message", "Book with ID " + id + " not found.");
        expectedBody.put("httpStatus", HttpStatus.NOT_FOUND);

        ResponseEntity<?> response = bookStoreController.getBookById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        assertEquals(expectedBody, response.getBody());
    }
}
