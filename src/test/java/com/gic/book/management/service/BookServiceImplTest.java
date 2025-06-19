package com.gic.book.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gic.book.management.helper.IsbnGeneratorHelper;
import com.gic.book.management.model.Book;
import com.gic.book.management.repository.BookRepository;
import com.gic.book.management.service.impl.BookServiceImpl;


@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Sample Title", "Sample Author", IsbnGeneratorHelper.generateIsbn13());
    }

    @Test
    void addBook_returnsNull_whenBookAlreadyExists() {
        when(bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())).thenReturn(true);
        Book result = bookService.addBook(book);
        assertNull(result);
    }

    @Test
    void addBook_returnsNewBook_whenBookDoesNotExist() {
        when(bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())).thenReturn(false);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.addBook(book);
        assertNotNull(result);
        assertEquals(book.getTitle(), result.getTitle());
    }

    @Test
    void updateBook_returnsNull_whenBookDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        Book result = bookService.updateBook(id, book);
        assertNull(result);
    }

    @Test
    void updateBook_returnsUpdatedBook_whenBookExists() {
        UUID id = UUID.randomUUID();
        Book existingBook = new Book("Old Title", "Old Author", book.getIsbn());

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.updateBook(id, book);

        assertNotNull(result);
        assertEquals(book.getTitle(), result.getTitle());
        assertEquals(book.getAuthor(), result.getAuthor());
    }

    @Test
    void deleteBook_returnsFalse_whenBookDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(bookRepository.existsById(id)).thenReturn(false);

        assertFalse(bookService.deleteBook(id));
    }

    @Test
    void deleteBook_returnsTrue_whenBookExists() {
        UUID id = UUID.randomUUID();
        when(bookRepository.existsById(id)).thenReturn(true);

        boolean result = bookService.deleteBook(id);

        verify(bookRepository).deleteById(id);
        assertTrue(result);
    }

    @Test
    void getBookById_returnsBook_whenBookExists() {
        UUID id = UUID.randomUUID();
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(id);
        assertEquals(book, result);
    }

    @Test
    void getBookById_returnsNull_whenBookNotFound() {
        UUID id = UUID.randomUUID();
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertNull(bookService.getBookById(id));
    }

    @Test
    void getAllBooks_returnsListOfBooks() {
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(1, result.size());
    }

    @Test
    void searchByTitleOrAuthor_returnsMatchingBooks() {
        when(bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                anyString(), anyString(), any())).thenReturn(List.of(book));

        List<Book> result = bookService.searchByTitleOrAuthor("Sample", 0, 10, "id", "asc");
        assertFalse(result.isEmpty());
        assertEquals(book.getTitle(), result.get(0).getTitle());
    }
}
