package com.gic.book.management.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IsbnGeneratorHelperTest {
    
    @ParameterizedTest
    @CsvSource({
        "978030640615, 7",
        "978186197271, 2",
        "979123456789, 6",
        "978316148410, 0",
        "978047005902, 9"
    })
    void calculateCheckDigit_returnsCorrectValueForVariousInputs(String isbn12, int expectedCheckDigit) {
        int actualCheckDigit = IsbnGeneratorHelper.calculateCheckDigit(isbn12);
        assertEquals(expectedCheckDigit, actualCheckDigit, "Check digit mismatch for input: " + isbn12);
    }

    @Test
    void calculateCheckDigit_throwsExceptionWhenLengthIsNot12() {
        assertThrows(IllegalArgumentException.class, () -> {
            IsbnGeneratorHelper.calculateCheckDigit("1234567890"); // too short
        });

        assertThrows(IllegalArgumentException.class, () -> {
            IsbnGeneratorHelper.calculateCheckDigit("1234567890123"); // too long
        });
    }

    @RepeatedTest(10)
    void generateIsbn13_shouldReturnValid13DigitISBN() {
        String isbn = IsbnGeneratorHelper.generateIsbn13();

        assertNotNull(isbn);
        assertEquals(13, isbn.length());
        assertTrue(isbn.startsWith("978") || isbn.startsWith("979"));

        String isbn12 = isbn.substring(0, 12);
        char expectedCheckDigit = Character.forDigit(IsbnGeneratorHelper.calculateCheckDigit(isbn12), 10);
        assertEquals(expectedCheckDigit, isbn.charAt(12));
    }
}
