package com.gic.book.management.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gic.book.management.helper.IsbnGeneratorHelper;
import com.gic.book.management.model.Book;
import com.gic.book.management.repository.BookRepository;
import com.gic.book.management.service.BookService;

@Service
public class BookServiceImpl implements BookService {

   BookRepository bookRepository;
 
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }    

    /**
     * {@inheritDoc}
    */
    @Override
    public Book addBook(Book book) 
    {

        if (bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
            return null;
        }
    
        Book newBook = createBook(book.getTitle(), book.getAuthor());
        bookRepository.save(newBook);
        return newBook;
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Book updateBook(UUID id, Book book) 
    {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) 
        {
         return addBook(book);
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        bookRepository.save(existingBook);
        return existingBook;
    }

   /**
     * {@inheritDoc}
    */
    @Override
    public boolean deleteBook(UUID bookId) 
    {
        if (!bookRepository.existsById(bookId)) {
            return false;
        }

        bookRepository.deleteById(bookId);
        return true;
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Book getBookById(UUID bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public List<Book> searchByTitleOrAuthor(String queryText, int page, int size, String sortBy, String sortDir)
    {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(queryText, queryText, getPageable(page, size, sortBy, sortDir))
                .stream()
                .toList();
    }

    private Pageable getPageable(int page, int size, String sortBy, String sortDir) 
    {
        if (sortBy == null || sortBy.isBlank()) {
            return PageRequest.of(page, size, Sort.by("id").ascending());
        }

        Sort sort = sortDir.equalsIgnoreCase("desc") 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }

    private Book createBook(String title, String author) 
    {
        return new Book(title, author, IsbnGeneratorHelper.generateIsbn13());
    }
    
}
