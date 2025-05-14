package models;

import java.sql.*;

@SuppressWarnings("StringTemplateMigration")
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
        String createCreatures = """
                CREATE TABLE IF NOT EXISTS Creatures(
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Name TEXT UNIQUE NOT NULL,
                    Origin TEXT NOT NULL,
                    OriginID INTEGER NOT NULL,
                    Description TEXT NOT NULL
                );
            """;
        statement.executeUpdate(createCreatures);
        // Player level
        String createPlayer = """
                CREATE TABLE IF NOT EXISTS Player(
                    ID INTEGER PRIMARY KEY,
                    HighestOrigin INTEGER NOT NULL
                );
            """;
        statement.executeUpdate(createPlayer);
    }

    // Deletes all tables
    public void deleteTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS Creatures");
    }

    // Deletes all data from all tables
    @SuppressWarnings("SqlWithoutWhere")
    public void clearTables() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Creatures");
            statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name='Creatures'");
        } catch (SQLException e) {
            rollback();
            throw new SQLException(e);
        }
    }

    // Add a creature to database, returns false if creature already exists in database
    public boolean addCreature(Creature creature) throws SQLException {
        try {
            // Check if creature already exists
            String checkCreaturesSQL = "SELECT 1 FROM Creatures WHERE Name = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkCreaturesSQL);
            checkStmt.setString(1, creature.getName());
            ResultSet rs = checkStmt.executeQuery();
            // Creature already exists, return false
            if (rs.next()) {
                return false;
            }
            // models.Creature is unique, update and return true
            String insertUserSQL = "INSERT INTO Creatures (Name, Origin, OriginID, Description) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertUserSQL);
            insertStatement.setString(1, creature.getName());
            insertStatement.setString(2, creature.getOrigin());
            OriginFactory originFactory = new OriginFactory();
            insertStatement.setInt(3, originFactory.getOriginID(creature.getOrigin()));
            insertStatement.setString(4, creature.getDescription());
            insertStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            rollback();
            throw new SQLException(e);
        }
    }

    public void addPlayer(Player player) throws SQLException {
        try {
            String addPlayerSQL = "INSERT INTO Player(ID, HighestOrigin) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addPlayerSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, player.getHighestOrigin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void updateHighestOrigin(int highestOrigin) throws SQLException {
        try {
            String updateHighestOriginSQL = "UPDATE Player Set HighestOrigin = ? WHERE ID = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(updateHighestOriginSQL);
            preparedStatement.setInt(1, highestOrigin);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}