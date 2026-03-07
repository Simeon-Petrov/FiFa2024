package com.sirma.model;

public class Match {
    private int id;
    private int aTeamId;
    private int bTeamId;
    private String date;
    private String score;
    private int maxMinutes;

    public Match(int id, int aTeamId, int bTeamId, String date, String score) {
        this.id = id;
        this.aTeamId = aTeamId;
        this.bTeamId = bTeamId;
        this.date = date;
        this.score = score;
        if (score.contains("(")) { // score.contains("(") ? 120 : 90
            this.maxMinutes = 120;
        }else {
            this.maxMinutes = 90;
        }
    }

    public int getId() {
        return id;
    }

    public int getaTeamId() {
        return aTeamId;
    }

    public int getbTeamId() {
        return bTeamId;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }

    public int getMaxMinutes() {
        return maxMinutes;
    }

    @Override
    public String toString() {
        return "Match{id=" + id + ", score='" + score + ", maxMinutes=" + maxMinutes + '}';
    }
}
