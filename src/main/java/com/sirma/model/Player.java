package com.sirma.model;

public class Player {
    private int id;
    private int teamNumber;
    private String position;
    private String fullName;
    private int teamId;

    public Player(int id, int teamNumber, String position, String fullName, int teamId) {
        this.id = id;
        this.teamNumber = teamNumber;
        this.position = position;
        this.fullName = fullName;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getFullName() {
        return fullName;
    }

    public int getTeamId() {
        return teamId;
    }

    @Override
    public String toString() {
        return "Player{id=" + id + ", name" + fullName + " }";
    }
}
