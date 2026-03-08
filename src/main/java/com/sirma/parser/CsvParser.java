package com.sirma.parser;

import com.sirma.model.Match;
import com.sirma.model.Player;
import com.sirma.model.Record;
import com.sirma.model.Team;
import com.sirma.util.DateParser;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    private CsvReader csvReader = new CsvReader();

    public List<Player> parsePlayers(String filePath) {
        List<Player> players = new ArrayList<>();

        for (String line : csvReader.readLines(filePath)) {
            //String[] parts = line.split(",\\s*|;\\s*|\\|\\s*|\\t\\s*"); regex remove spaces (-trim)
            //String[] parts = line.split("[,;|\\t]"); + trim
            String[] parts = line.split(",|;|\\||\\\\|\\t"); // ,  ;  |  \   tab
            int id = Integer.parseInt(parts[0].trim());
            int teamNumber = Integer.parseInt(parts[1].trim());
            String position = parts[2].trim();
            String fullName = parts[3].trim();
            int teamId = Integer.parseInt(parts[4].trim());

            players.add(new Player(id, teamNumber, position, fullName, teamId));
        }
        return players;
    }

    public List<Team> parseTeams(String filePath) {
        List<Team> teams = new ArrayList<>();

        for (String line : csvReader.readLines(filePath)) {
            String[] parts = line.split(",|;|\\||\\\\|\\t");
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String managerFullName = parts[2].trim();
            String group = parts[3].trim();

            teams.add(new Team(id, name, managerFullName, group));
        }
        return teams;
    }

    public List<Match> parseMatches(String filePath) {
        List<Match> matches = new ArrayList<>();

        for(String line : csvReader.readLines(filePath)) {
            String[] parts = line.split(",|;|\\||\\\\|\\t");
            int id = Integer.parseInt(parts[0].trim());
            int aTeamId = Integer.parseInt(parts[1].trim());
            int bTeamId = Integer.parseInt(parts[2].trim());
            String date = DateParser.parseToString(parts[3].trim());
            String score = parts[4].trim();

            matches.add(new Match(id, aTeamId, bTeamId, date, score));
        }
        return matches;
    }

    public List<Record> parseRecords(String filePath, List<Match> matches) {
        List<Record> records = new ArrayList<>();

        for (String line : csvReader.readLines(filePath)) {
            String[] parts = line.split(",|;|\\||\\\\|\\t");
            int id = Integer.parseInt(parts[0].trim());
            int playerId = Integer.parseInt(parts[1].trim());
            int matchId = Integer.parseInt(parts[2].trim());
            int fromMinutes = Integer.parseInt(parts[3].trim());

            int toMinutes;
            if (parts[4].trim().equals("NULL")) {
                toMinutes = getMaxMinutesForMatch(matchId, matches);
            }else {
                toMinutes = Integer.parseInt(parts[4].trim());
            }

            records.add(new Record(id, playerId, matchId, fromMinutes, toMinutes));
        }
        return records;
    }

    // if score.contains("(") => 120
    private int getMaxMinutesForMatch (int matchId, List<Match> matches) {
        for (Match match : matches) {
            if (match.getId() == matchId) {
                return match.getMaxMinutes();
            }
        }
        return 90;
    }
}
