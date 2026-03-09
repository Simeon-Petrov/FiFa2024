package com.sirma.database;

import com.sirma.model.Match;

import java.sql.*;

public class MatchRepository {

    public void addMatch(Match match) {
        String sql = "INSERT INTO matches (id, a_team_id, b_team_id, date, score, max_minutes) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, match.getId());
            stmt.setInt(2, match.getATeamId());
            stmt.setInt(3, match.getBTeamId());
            stmt.setString(4, match.getDate());
            stmt.setString(5, match.getScore());
            stmt.setInt(6, match.getMaxMinutes());
            stmt.executeUpdate();
            System.out.println("Match added: " + match.getId());

        } catch (SQLException e) {
            System.out.println("Error adding match: " + e.getMessage());
        }
    }

    public void viewMatch(int id) {
        String sql = "SELECT * FROM matches WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("********************************");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Team A id: " + rs.getInt("a_team_id"));
                System.out.println("Team B id: " + rs.getInt("b_team_id"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Score: " + rs.getString("score"));
                System.out.println("Max Minutes: " + rs.getInt("max_minutes"));
                System.out.println("********************************");

            } else {
                System.out.println("Match not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing match: " + e.getMessage());
        }
    }

    public void updateMatch(int id, String score, String date) {
        String sql = "UPDATE matches SET score = ?, date = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, score);
            stmt.setString(2, date);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Match updated successfully!");
            } else {
                System.out.println("Match not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating match: " + e.getMessage());
        }
    }

    public void deleteMatch(int id) {
        String sql = "DELETE FROM matches WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0 ){
                System.out.println("Match deleted successfully");
            } else {
                System.out.println("Match not found");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting match: " + e.getMessage());
        }
    }
}