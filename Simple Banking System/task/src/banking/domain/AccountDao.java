package banking.domain;

import banking.config.DataSourceFactory;
import banking.utils.AccountGenerator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class AccountDao {
    private DataSource dataSource;

    public AccountDao(String url) {
        this.dataSource = DataSourceFactory.createDatabase(url);
    }
    public Account create(Long id) {

        Account account = AccountGenerator.create(id);
        insert(account);
        return account;
    }

    private void insert(Account account) {
        try (Connection conn = dataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("INSERT INTO card VALUES("
                        + account.getId() + ", "
                        + "'" + account.getCardNumber() + "'" + ", "
                        + "'" + account.getPin() + "'" + ", "
                        + account.getBalance()
                        + ")");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        };
    }

    public Optional<Account> findByCardNumber(String cardNumber) {
        Account account = null;
        try (Connection conn = dataSource.getConnection()) {
            try (Statement statement = conn.createStatement();) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM card WHERE number = " + cardNumber)) {
                    while (rs.next()) {
                        account = new Account(rs);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(account);
    }


    public Long findNewId() {
        try (Connection conn = dataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                try (ResultSet id = statement.executeQuery("SELECT id FROM card ORDER BY id DESC LIMIT 1")) {
                    while (id.next()) {
                        return id.getLong("id") + 1L;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 1L;
    }

    public void findAll() {
        try (Connection conn = dataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT * FROM card");
                while (rs.next()) {
                    System.out.println(rs.getString("number") + " " + rs.getString("pin"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
