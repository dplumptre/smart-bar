package com.example.smartBar.smartBar.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Helper {

    public static String generateRandomString(int length) {
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseAlphabet = lowerCaseAlphabet.toUpperCase();
        String digits = "0123456789";
        String allCharacters =  upperCaseAlphabet + digits;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            stringBuilder.append(allCharacters.charAt(randomIndex));
        }
        return stringBuilder + formattedDate;
    }
}
