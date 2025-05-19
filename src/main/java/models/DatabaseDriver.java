package models;

import creatureGroups.CreatureGroup;

import java.sql.*;
import java.util.ArrayList;

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
                    Description TEXT NOT NULL
                );
            """;
        statement.executeUpdate(createCreatures);
        // Player level
        String createPlayer = """
                CREATE TABLE IF NOT EXISTS Player(
                    ID INTEGER PRIMARY KEY,
                    HighestRegion INTEGER NOT NULL
                );
            """;
        statement.executeUpdate(createPlayer);
    }

    // Deletes all tables
    public void deleteTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS Creatures");
        statement.executeUpdate("DROP TABLE IF EXISTS Player");
    }

    // Deletes all data from all tables
    @SuppressWarnings("SqlWithoutWhere")
    public void clearTables() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Creatures");
            statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name='Creatures'");
            commit();
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
            // Creature is unique, update and return true
            String insertUserSQL = "INSERT INTO Creatures (Name, Origin, Description) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertUserSQL);
            insertStatement.setString(1, creature.getName());
            insertStatement.setString(2, creature.getOrigin());
            insertStatement.setString(3, creature.getDescription());
            insertStatement.executeUpdate();
            commit();
            return true;
        }
        catch (SQLException e) {
            rollback();
            throw new SQLException(e);
        }
    }

    public ArrayList<Creature> getCreatures() throws SQLException {
        try {
            ArrayList<Creature> creatures = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Creatures");
            // Loop through all creatures
            while (rs.next()) {
                String name = rs.getString("Name");
                String origin = rs.getString("Origin");
                CreatureGroup creatureGroup = OriginFactory.getCreatureGroup(origin);
                creatures.add(creatureGroup.getCreature(name));
            }
            return creatures;
        } catch (SQLException e) {
            throw new SQLException("Error retrieving creatures from database");
        }
    }

    public boolean addPlayer(Player player) throws SQLException {
        try {
            // Check if player is already entered in system
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Player");
            // Player already logged, return false
            if (rs.next()) {
                return false;
            }
            // models.Creature is unique, update and return true
            String addPlayerSQL = "INSERT INTO Player(ID, HighestRegion) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addPlayerSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, player.getHighestRegion());
            preparedStatement.executeUpdate();
            commit();
            return true;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Player getPlayer() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Player");
            if (rs.next()) {
                int highestRegion = rs.getInt("HighestRegion");
                return new Player(getCreatures(), highestRegion);
            } else {
                throw new SQLException("No player in database");
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving player from database");
        }
    }

    public void alterHighestRegion(int highestRegion) throws SQLException {
        try {
            String updateHighestOriginSQL = "UPDATE Player Set HighestRegion = ? WHERE ID = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(updateHighestOriginSQL);
            preparedStatement.setInt(1, highestRegion);
            commit();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}