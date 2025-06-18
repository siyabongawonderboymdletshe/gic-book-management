package com.gic.book.management.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private final List<String> errors = new ArrayList<>();

    public ValidationResult addError(String error) {
        errors.add(error);
        return this;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return String.join("; ", errors);
    }
}
