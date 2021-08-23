package banking.view;

import banking.domain.Command;
import banking.dto.LoginRequestDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainView  {

    public static final String CREATE_AN_ACCOUNT = "1. Create an account";
    public static final String LOG_INTO_ACCOUNT = "2. Log into account";
    public static final String EXIT = "0. Exit";
    public static final String PROMPT = ">";
    public static final String ENTER_YOUR_CARD_NUMBER = "Enter your card Number:";
    public static final String ENTER_YOUR_PIN = "Enter your PIN";
    public static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("1", Command.CREATE);
        commands.put("2", Command.LOGIN);
        commands.put("0", Command.EXIT);
    }


    public static Command showMenu(Scanner scanner) {
        System.out.println(CREATE_AN_ACCOUNT);
        System.out.println(LOG_INTO_ACCOUNT);
        System.out.println(EXIT);
        System.out.print(PROMPT);
        String choice = scanner.nextLine();
        Command command = commands.getOrDefault(choice, Command.INVALID);
        return command;
    }

    public static LoginRequestDto login(Scanner scanner) {
        System.out.println();
        System.out.println(ENTER_YOUR_CARD_NUMBER);
        System.out.print(PROMPT);
        String cardNumber = scanner.nextLine();
        System.out.println(ENTER_YOUR_PIN);
        System.out.print(PROMPT);
        String pin = scanner.nextLine();
        System.out.println();
        return new LoginRequestDto(cardNumber, pin);
    }


}
