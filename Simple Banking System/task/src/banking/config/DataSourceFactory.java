package banking.config;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceFactory {

    public static DataSource createDatabase(String url) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:" + url);
        createTable(dataSource);
        return dataSource;
    }

    private static void createTable(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            try(Statement statement = conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER NOT NULL,"+
                        "number TEXT NOT NULL,"+
                        "pin VARCHAR(4) NOT NULL,"+
                        "balance INTEGER DEFAULT 0)");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
