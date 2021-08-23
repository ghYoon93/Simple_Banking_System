package banking.domain;

import banking.dto.LoginResponseDto;

public class Session {
    private boolean isLogin;
    private Long balance;

    public Session() {
        isLogin = false;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void logOut() {
        balance = 0L;
        isLogin = false;
    }

    public void logIn(LoginResponseDto login) {
        this.balance = login.getBalance();
        isLogin = true;
    }

    public Long getBalance() {
        return balance;
    }
}
