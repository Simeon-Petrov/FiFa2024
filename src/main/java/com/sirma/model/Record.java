package com.sirma.model;

public class Record {
    private int id;
    private int playerId;
    private int matchId;
    private int fromMinutes;
    private int toMinutes;

    public Record(int id, int playerId, int matchId, int fromMinutes, int toMinutes) {
        this.id = id;
        this.playerId = playerId;
        this.matchId = matchId;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getFromMinutes() {
        return fromMinutes;
    }

    public int getToMinutes() {
        return toMinutes;
    }

    @Override
    public String toString() {
        return "Record{playerId=" + playerId + ", matchId=" + matchId +
                ", from=" + fromMinutes + ", to=" + toMinutes + '}';

    }
}
