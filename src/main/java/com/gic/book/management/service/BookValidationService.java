package com.gic.book.management.service;

import com.gic.book.management.validation.ValidationResult;

public interface BookValidationService {
    ValidationResult validate(String title, String author);
}
