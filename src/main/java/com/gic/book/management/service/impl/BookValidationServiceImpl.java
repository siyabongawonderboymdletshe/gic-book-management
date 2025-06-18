package com.gic.book.management.service.impl;

import org.springframework.stereotype.Service;

import com.gic.book.management.service.BookValidationService;
import com.gic.book.management.validation.ValidationResult;

@Service
public class BookValidationServiceImpl implements BookValidationService {
    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_AUTHOR_LENGTH = 50;

    public ValidationResult validate(String title, String author) {
        ValidationResult result = new ValidationResult();

        if (title == null || title.trim().isEmpty()) {
            result.addError("Title must not be blank.");
        } else if (title.length() > MAX_TITLE_LENGTH) {
            result.addError("Title must not exceed 100 characters.");
        }

        if (author == null || author.trim().isEmpty()) {
            result.addError("Author must not be blank.");
        } else if (author.length() > MAX_AUTHOR_LENGTH) {
            result.addError("Author must not exceed 50 characters.");
        }

        return result;
    }
    
}
