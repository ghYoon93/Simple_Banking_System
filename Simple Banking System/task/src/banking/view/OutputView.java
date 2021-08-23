package banking.view;

import banking.domain.Session;
import banking.dto.AccountDto;

public class OutputView {

    public static final String EXIT = "Bye!";
    public static final String PIN = "Your card PIN:";
    public static final String CARD_NUMBER = "Your card number:";
    public static final String CREATED = "Your card has been created";
    public static final String LOGGED_IN = "You have successfully logged in!";
    public static final String LOGGED_OUT = "You have successfully logged out!";
    public static final String BALANCE = "Balance: ";

    public static void exit() {
        System.out.println(EXIT);
    }

    public static void accountCreated(AccountDto accountDto) {
        System.out.println();
        System.out.println(CREATED);
        System.out.println(CARD_NUMBER);
        System.out.println(accountDto.getCardNumber());
        System.out.println(PIN);
        System.out.println(accountDto.getPin());
        System.out.println();

    }

    public static void loginFail(String message) {
        System.out.println(message);
    }

    public static void loginSuccess() {
        System.out.println(LOGGED_IN);
        System.out.println();
    }

    public static void logOut() {
        System.out.println();
        System.out.println(LOGGED_OUT);
        System.out.println();
    }

    public static void balance(Session session) {
        System.out.println();
        System.out.print(BALANCE);
        System.out.println(session.getBalance());
        System.out.println();
    }

    public static void invalid() {
    }
}
