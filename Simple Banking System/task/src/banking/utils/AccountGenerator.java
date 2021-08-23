package banking.utils;

import banking.domain.Account;

import java.util.Random;

public class AccountGenerator {
    private static final String BIN = "400000";

    public static Account create(Long id) {
        String cardNumber = BIN + String.format("%09d", id);
        String created = generateCardNumber(cardNumber);
        String pin = generatePin();
        return new Account(id, created, pin);
    }

    private static String generatePin() {
        Random random = new Random();
        StringBuilder pin = new StringBuilder();
        while(pin.length() < 4) {
            pin.append(random.nextInt(10));
        }
        return pin.toString();
    }

    private static String generateCardNumber(String cardNumber) {
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int number = charToInt(cardNumber.charAt(i));
            number = applyLuhn(i, number);
            sum += number;
        }

        int checkSum = 0;
        if (sum % 10 != 0) {
            checkSum = 10 - sum % 10;
        }

        cardNumber += String.valueOf(checkSum);
        return cardNumber;
    }

    private static int charToInt(char original) {
        return original - '0';
    }

    private static int applyLuhn(int position, int number) {
        if (isOdd(position)) {
            number = multiply(number);
        }
        number = substract(number);
        return number;
    }

    private static int substract(int number) {
        if (number > 9) {
            number -= 9;
        }
        return number;
    }

    private static int multiply(int number) {
        return number * 2;
    }

    private static boolean isOdd(int position) {
        return position % 2 == 0;
    }
}