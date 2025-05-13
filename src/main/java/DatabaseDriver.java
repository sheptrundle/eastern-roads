import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver {
    private final String sqliteFilename;
    private Connection connection;

    public DatabaseDriver() {
        this.sqliteFilename = "eastern-roads.sqlite";
    }

    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            throw new IllegalStateException("The connection is already opened");
        }
        connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteFilename);
        connection.createStatement().execute("PRAGMA foreign_keys = ON");
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        // MyCreatures
        String createMyCreatures = """
                CREATE TABLE IF NOT EXISTS MyCreatures(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Name TEXT NOT NULL,
                    Origin TEXT NOT NULL,
                    Description TEXT NOT NULL,
                    PrimaryAttack TEXT NOT NULL,
                    SecondaryAttack TEXT NOT NULL,
                    Ability TEXT NOT NULL,
                    Passive TEXT NOT NULL
                );
            """;
        statement.executeUpdate(createMyCreatures);
    }
}