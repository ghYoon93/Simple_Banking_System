package banking.interfaces;

import banking.application.AccountService;
import banking.domain.AccountDao;
import banking.domain.Command;
import banking.domain.Session;
import banking.dto.AccountDto;
import banking.dto.LoginRequestDto;
import banking.dto.LoginResponseDto;
import banking.view.CustomerView;
import banking.view.MainView;
import banking.view.OutputView;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private final Scanner scanner = new Scanner(System.in);
    private final AccountService accountService;
    private Session session;
    private Map<Command, Runnable> mapper;

    public Bank(String url) {
        accountService = new AccountService(new AccountDao(url));
        session =  new Session();
        generateMap();
    }

    public void run() {
        Command command = showMenu();
        execute(command);
    }

    private Command showMenu() {
        if (session.isLogin()) {
            return CustomerView.showMenu(scanner);
        }
        return MainView.showMenu(scanner);
    }

    private void execute(Command command) {
        mapper.get(command).run();
    }

    private void create() {
        AccountDto accountDto = accountService.create();
        OutputView.accountCreated(accountDto);
        run();
    }

    private void balance() {
        OutputView.balance(session);
        run();
    }

    private void login() {
        LoginRequestDto loginRequestDto = MainView.login(scanner);
        try {
            LoginResponseDto loginResponseDto = accountService.login(loginRequestDto);
            session.logIn(loginResponseDto);
            OutputView.loginSuccess();
        } catch (IllegalArgumentException e) {
            OutputView.loginFail(e.getMessage());
        }
        run();
    }

    private void exit() {
        OutputView.exit();
    }

    private void logout() {

        session.logOut();
        OutputView.logOut();
        run();
    }

    private void invalid() {
        OutputView.invalid();
    }

    private void generateMap() {
        mapper = new HashMap<>();
        mapper.put(Command.CREATE, this::create);
        mapper.put(Command.BALANCE, this::balance);
        mapper.put(Command.LOGIN, this::login);
        mapper.put(Command.LOG_OUT, this::logout);
        mapper.put(Command.EXIT, this::exit);
        mapper.put(Command.INVALID, this::invalid);
    }


}
