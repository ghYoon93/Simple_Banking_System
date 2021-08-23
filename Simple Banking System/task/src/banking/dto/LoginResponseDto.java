package banking.dto;

import banking.domain.Account;

public class LoginResponseDto {
    private final Long balance;

    public LoginResponseDto(Account account) {
        this.balance = account.getBalance();
    }

    public Long getBalance() {
        return balance;
    }
}
