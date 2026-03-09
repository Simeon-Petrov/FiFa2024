package com.sirma.database;

import com.sirma.model.Team;

import java.sql.*;

public class TeamRepository {

    public void addTeam(Team team) {
        String sql = "INSERT INTO teams (id, name, manager_full_name, group_name) "+
                     "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,team.getId());
            stmt.setString(2, team.getName());
            stmt.setString(3, team.getManagerFullName());
            stmt.setString(4, team.getGroup());
            stmt.executeUpdate();
            System.out.println("Team added: " + team.getName());

        } catch (SQLException e) {
            System.out.println("Error adding team: " + e.getMessage());
        }
    }

    public void viewTeam(int id) {
        String sql = "SELECT * FROM teams WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                System.out.println("********************************");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Manager: " + rs.getString("manager_full_name"));
                System.out.println("Group: " + rs.getString("group_name"));
                System.out.println("********************************");
            } else {
                System.out.println("Team not found!");
            }


        } catch (SQLException e) {
            System.out.println("Error view team: " + e.getMessage());
        }
    }

    public void updateTeam(int id, String name, String managerFullName) {
        String sql = "UPDATE teams SET name = ?, manager_full_name = ? WHERE id =?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, managerFullName);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();

            if (rows > 0 ) {
                System.out.println("Team updated successfully!");
            }else{
                System.out.println("Team not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating team: " + e.getMessage());
        }
    }

    public void deleteTeam(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0 ) {
                System.out.println("Team deleted successfully!");
            }else {
                System.out.println("Team not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting team: " + e.getMessage());
        }
    }
}
