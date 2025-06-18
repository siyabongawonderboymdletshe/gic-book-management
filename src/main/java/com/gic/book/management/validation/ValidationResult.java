package com.gic.book.management.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the result of a validation process.
 * It contains a list of errors and methods to check if the validation is valid,
 * add errors, and retrieve error messages.
 */
public class ValidationResult {
    private final List<String> errors = new ArrayList<>();

    /**
     * Adds an error message to the validation result.
     *
     * @param error the error message to add
     * @return the current ValidationResult instance for method chaining
     */
    public ValidationResult addError(String error) {
        errors.add(error);
        return this;
    }

    /**
     * Checks if the validation result is valid (i.e., contains no errors).
     *
     * @return true if there are no errors, false otherwise
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Retrieves the list of error messages.
     *
     * @return a list of error messages
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Retrieves a single error message by joining all errors with a semicolon.
     *
     * @return a string containing all error messages joined by semicolons
     */
    public String getErrorMessage() {
        return String.join("; ", errors);
    }
}
