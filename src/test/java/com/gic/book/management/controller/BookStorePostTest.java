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
class BookStorePostTest {
    @InjectMocks
    private BookController bookStoreController;

    @Mock
    private BookService bookStoreService;

    @Test
    void addBook_shouldReturnAddedBookIfItDoesNoExist() {

        BookRequestDto dTo = new BookRequestDto("Valid Title", "Valid Author");
        Book added = new Book(dTo.getTitle(), dTo.getAuthor());

        Mockito.when(bookStoreService.addBook(Mockito.any(Book.class)))
           .thenReturn(added);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book created successfully");
        expectedResponse.put("httpStatus", HttpStatus.CREATED);
        expectedResponse.put("data", added);

        ResponseEntity<?> response = bookStoreController.addBook(dTo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void addBook_shouldReturnConflictErrorIfBookExists() {

        BookRequestDto dTo = new BookRequestDto("Valid Title", "Valid Author");
       
        Mockito.when(bookStoreService.addBook(Mockito.any(Book.class)))
           .thenReturn(null);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Book with the same title and author already exists.");
        expectedResponse.put("httpStatus", HttpStatus.CONFLICT);

        ResponseEntity<?> response = bookStoreController.addBook(dTo);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
