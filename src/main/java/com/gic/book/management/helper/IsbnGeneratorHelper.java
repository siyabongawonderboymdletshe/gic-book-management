package com.gic.book.management.helper;

import java.util.Random;

public class IsbnGeneratorHelper {
     private static final Random RANDOM = new Random();

    public static String generateIsbn13() {
        StringBuilder isbn = new StringBuilder();

        // Start with the standard ISBN-13 prefix: 978 or 979
        isbn.append("978");

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

    private static int calculateCheckDigit(String isbn12) {
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
