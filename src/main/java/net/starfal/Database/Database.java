package net.starfal.Database;

import net.starfal.SFRanks;
import org.bukkit.Bukkit;

import java.sql.*;

public class Database {

    private final Connection connection;

    public Database(String path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);

        try(Statement statement = connection.createStatement()){
            statement.execute("""
                CREATE TABLE IF NOT EXISTS groups (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    prefix TEXT,
                    suffix TEXT,
                    isDefault BOOLEAN NOT NULL,
                    permissions LIST
                )
                """);
            statement.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    nickname TEXT NOT NULL,
                    groupIds LIST NOT NULL,
                    prefix TEXT,
                    suffix TEXT,
                )
                """);
            statement.execute("""
                CREATE TABLE IF NOT EXISTS group_paths (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    groupIds LIST NOT NULL
                )
                """);
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }

    public boolean userExists(String nickname) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE nickname = ?")){
            statement.setString(1, nickname);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }
    }
    public boolean groupExists(String name) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups WHERE name = ?")){
            statement.setString(1, name);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }
    }
    public boolean groupPathExists(String name) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM group_paths WHERE name = ?")){
            statement.setString(1, name);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }
    }
}
