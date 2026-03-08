package com.sirma.database;

import com.sirma.model.Match;
import com.sirma.model.Player;
import com.sirma.model.Record;
import com.sirma.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseWriter {

    public void insertTeams(List<Team> teams) {
        String sql = "INSERT INTO teams (id, name, manager_full_name, group_name) " +
                     "VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Team team : teams) {
                stmt.setInt(1, team.getId());  //sql start from 1
                stmt.setString(2, team.getName());
                stmt.setString(3, team.getManagerFullName());
                stmt.setString(4, team.getGroup());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error inserting teams: " + e.getMessage());
        }
    }

    public void insertPlayers(List<Player> players) {
        String sql = "INSERT INTO players (id, team_number, position, full_name, team_id) " +
                     "VALUES (?, ?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Player player : players) {
                stmt.setInt(1, player.getId());
                stmt.setInt(2, player.getTeamNumber());
                stmt.setString(3, player.getPosition());
                stmt.setString(4, player.getFullName());
                stmt.setInt(5, player.getTeamId());
                stmt.executeUpdate();
            }

            System.out.println("Player inserted: " + players.size());

        } catch (SQLException e) {
            System.out.println("Error inserting players: " + e.getMessage());
        }
    }

    public void insertMatches(List<Match> matches) {
        String sql = "INSERT INTO matches (id, a_team_id, b_team_id, date, score, max_minutes) " +
                     "VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Match match : matches) {
                stmt.setInt(1, match.getId());
                stmt.setInt(2, match.getATeamId());
                stmt.setInt(3, match.getBTeamId());
                stmt.setString(4, match.getDate());
                stmt.setString(5, match.getScore());
                stmt.setInt(6, match.getMaxMinutes());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error inserting matches: " + e.getMessage());
        }
    }

    public void insertRecords(List<Record> records) {
        String sql = "INSERT INTO records (id, player_id, match_id, from_minutes, to_minutes) " +
                     "VALUES (?, ?, ?, ?, ? ) ON CONFLICT (id) DO NOTHING";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            for (Record record : records) {
                stmt.setInt(1, record.getId());
                stmt.setInt(2, record.getPlayerId());
                stmt.setInt(3, record.getMatchId());
                stmt.setInt(4, record.getFromMinutes());
                stmt.setInt(5, record.getToMinutes());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error inserting records: " + e.getMessage());
        }
    }
}
