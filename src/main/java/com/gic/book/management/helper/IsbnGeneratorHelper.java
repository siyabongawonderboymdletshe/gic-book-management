package com.gic.book.management.helper;

import java.util.Random;

/**
 * This class provides a utility to generate a random ISBN-13 number.
 * It generates a valid ISBN-13 by creating a prefix, generating random digits,
 * and calculating the check digit according to the ISBN-13 standard.
 */
public class IsbnGeneratorHelper {
    private static final Random RANDOM = new Random();

    public static String generateIsbn13() {
        StringBuilder isbn = new StringBuilder();

        // Start with a random ISBN-13 prefix: 978 or 979
        String prefix = RANDOM.nextBoolean() ? "978" : "979";
        isbn.append(prefix);

        // Generate next 9 random digits (to reach 12 total before check digit)
        for (int i = 0; i < 9; i++) {
            isbn.append(RANDOM.nextInt(10));
        }

        // Calculate the check digit
        String isbnWithoutCheckDigit = isbn.toString();
        int checkDigit = calculateCheckDigit(isbnWithoutCheckDigit);

        // Append check digit
        isbn.append(checkDigit);

        return isbn.toString();
    }

    public static int calculateCheckDigit(String isbn12) {
        if (isbn12.length() != 12) {
            throw new IllegalArgumentException("ISBN must be 12 digits long for check digit calculation.");
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn12.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        return (10 - (sum % 10)) % 10;
    }
}
