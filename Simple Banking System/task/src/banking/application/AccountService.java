package banking.application;

import banking.domain.Account;
import banking.domain.AccountDao;
import banking.dto.AccountDto;
import banking.dto.LoginRequestDto;
import banking.dto.LoginResponseDto;
import banking.utils.AccountGenerator;

import java.sql.SQLException;

public class AccountService {
    public static final String WRONG_CARD_NUMBER_OR_PIN = "Wrong card number or PIN!";
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountDto create() {
        Long id = accountDao.findNewId();
        Account created = accountDao.create(id);
        return new AccountDto(created);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String cardNumber = loginRequestDto.getCardNumber();
        String pin = loginRequestDto.getPin();
        Account account = accountDao.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException(WRONG_CARD_NUMBER_OR_PIN));
        account.validatePin(pin);
        return new LoginResponseDto(account);
    }

    public void findAll() {
        accountDao.findAll();
    }
}
