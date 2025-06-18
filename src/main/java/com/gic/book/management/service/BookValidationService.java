package com.gic.book.management.service;

import com.gic.book.management.validation.ValidationResult;

/**
 * This interface defines a service for validating book details such as title and author.
 * It provides a method to validate these fields and returns a ValidationResult containing any errors.
 */
public interface BookValidationService {
    /**
     * Validates the title and author of a book.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @return a ValidationResult containing any validation errors
     */
    ValidationResult validate(String title, String author);
}
