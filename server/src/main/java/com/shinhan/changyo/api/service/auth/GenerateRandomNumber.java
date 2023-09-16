package com.shinhan.changyo.api.service.auth;

import java.util.Random;

public class GenerateRandomNumber {

    public static String generateRandomNumber(int size) {
        int bound = (int) Math.pow(10, size);

        Random random = new Random();
        int number = random.nextInt(bound);
        String format = "%0" + size + "d";
        return String.format(format, number);
    }
}
