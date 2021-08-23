package banking.dto;

import banking.domain.Account;

public class AccountDto {
    private String cardNumber;
    private String pin;

    public AccountDto(Account account) {
        this.cardNumber = account.getCardNumber();
        this.pin = account.getPin();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }
}
