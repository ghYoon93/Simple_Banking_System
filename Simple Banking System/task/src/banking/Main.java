package banking;

import banking.interfaces.Bank;

public class Main {
    public static void main(String[] args) {
        new Bank(args[1]).run();
    }
}