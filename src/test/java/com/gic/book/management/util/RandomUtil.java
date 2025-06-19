package com.gic.book.management.util;

public class RandomUtil {
    public static String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        while (length-- > 0) {
            int index = (int) (Math.random() * characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
}
