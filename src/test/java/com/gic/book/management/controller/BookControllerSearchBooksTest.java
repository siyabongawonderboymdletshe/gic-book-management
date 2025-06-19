package com.gic.book.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
class BookControllerSearchBooksTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void searchBook_returnsBookList_whenBookExist() {
        Book book1 = new Book("Valid Title 1", "Valid Author 1");
        Book book2 = new Book("Valid Title 2", "Valid Author 2");
        List<Book> books = List.of(book1, book2);

        Mockito.when(bookService.searchByTitleOrAuthor("v", 5, 5, "title", "asce")).thenReturn(books);
        
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Search completed");
        expectedResponse.put("httpStatus", HttpStatus.OK);
        expectedResponse.put("data", books);

        ResponseEntity<?> response = bookController.searchBooks("v", 5, 5, "title", "asce");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getBook_returnsEmptyList_whenNoBooksFound() {
        String queryText = "text";
        Mockito.when(bookService.searchByTitleOrAuthor(queryText, 5, 5, "title", "asce")).thenReturn(Collections.emptyList());

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "No books found matching the query: " + queryText);
        expectedResponse.put("httpStatus", HttpStatus.OK);
        expectedResponse.put("data", Collections.emptyList());

      ResponseEntity<?> response = bookController.searchBooks(queryText, 5, 5, "title", "asce");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
