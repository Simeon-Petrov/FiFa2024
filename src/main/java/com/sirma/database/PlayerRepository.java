package com.sirma.database;

import com.sirma.model.Player;

import java.sql.*;

public class PlayerRepository {

    public void addPlayer(Player player) {
        String sql = "INSERT INTO players (id, team_number, position, full_name, team_id) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, player.getId());
            stmt.setInt(2, player.getTeamNumber());
            stmt.setString(3, player.getPosition());
            stmt.setString(4, player.getFullName());
            stmt.setInt(5, player.getTeamId());
            stmt.executeUpdate();
            System.out.println("Player added: " + player.getFullName());

        } catch (SQLException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }

    public void viewPlayer(int id) {
        String sql = "SELECT * FROM players WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("********************************");
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Name: " + rs.getString("full_name"));
                System.out.println("Position: " + rs.getString("position"));
                System.out.println("Team Number: " + rs.getInt("team_number"));
                System.out.println("Team ID: " + rs.getInt("team_id"));
                System.out.println("********************************");
            } else {
                System.out.println("Player not found");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing player: " + e.getMessage());
        }
    }

    public void updatePlayer(int id, String position, String fullName) {
        String sql = "UPDATE player SET position = ?, full_name = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, position);
            stmt.setString(2, fullName);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Player updated successfully");
            } else {
                System.out.println("Player not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating player: " + e.getMessage());
        }
    }

    public void deletePlayer(int id) {
        String sql = "DELETE FROM players WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows= stmt.executeUpdate();

            if (rows > 0 ) {
                System.out.println("Player deleted successfully!");
            }else {
                System.out.println("Player not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting player: " + e.getMessage());
        }
    }
}
