package banking.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private static Long id;
    public static final String WRONG_CARD_NUMBER_OR_PIN = "Wrong card number or PIN!";
    private  String cardNumber;
    private String pin;
    private Long balance;


    public Account(Long id, String cardNumber, String pin) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = 0L;
    }

    public Account(ResultSet account) throws SQLException {
        this.id = account.getLong("id");
        this.cardNumber = account.getString("number");
        this.pin = account.getString("pin");
        this.balance = account.getLong("balance");
    }


    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public Long getBalance() {
        return balance;
    }

    public void validatePin(String pin) {
        if (!this.pin.equals(pin)) {
            throw new IllegalArgumentException(WRONG_CARD_NUMBER_OR_PIN);
        };
    }
}
