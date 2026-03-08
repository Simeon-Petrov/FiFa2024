package com.sirma.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {

    private static final String CONFIG_FILE = "config.properties";

    private static Properties loadConfig() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Error reading config.properties: " + e.getMessage());
        }
        return props;
    }

    public static Connection getConnection() throws SQLException {
        Properties props = loadConfig();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            //Java create tables
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS teams (
                        id INT PRIMARY KEY,
                        name VARCHAR(100),
                        manager_full_name VARCHAR(100),
                        group_name VARCHAR(10)
                    )
                    """);

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS players (
                        id INT PRIMARY KEY,
                        team_number INT,
                        position VARCHAR(10),
                        full_name VARCHAR(100),
                        team_id INT REFERENCES teams(id)
                    )
                    """);

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS matches (
                        id INT PRIMARY KEY,
                        a_team_id INT REFERENCES teams(id),
                        b_team_id INT REFERENCES teams(id),
                        date VARCHAR(20),
                        score VARCHAR(20),
                        max_minutes INT
                    )
                    """);

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS records (
                        id INT PRIMARY KEY,
                        player_id INT REFERENCES players(id),
                        match_id INT REFERENCES matches(id),
                        from_minutes INT,
                        to_minutes INT
                    )
                    """);


        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}

