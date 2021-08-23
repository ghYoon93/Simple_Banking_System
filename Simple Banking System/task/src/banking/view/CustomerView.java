package banking.view;

import banking.domain.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerView {

    public static final String BALANCE = "1. Balance";
    public static final String LOG_OUT_ACCOUNT = "2. Log out";
    public static final String EXIT = "0. Exit";

    private static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("1", Command.BALANCE);
        commands.put("2", Command.LOG_OUT);
        commands.put("0", Command.EXIT);
    }

    public static Command showMenu(Scanner scanner) {
        System.out.println(BALANCE);
        System.out.println(LOG_OUT_ACCOUNT);
        System.out.println(EXIT);

        String choice = scanner.nextLine();
        return commands.getOrDefault(choice, Command.INVALID);
    }
}
