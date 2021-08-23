package banking.domain;

public enum Command {

    CREATE("create"),
    BALANCE("balance"),
    LOGIN("login"),
    LOG_OUT("logout"),
    EXIT("exit"),
    INVALID("invalid");


    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
