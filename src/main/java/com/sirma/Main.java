package com.sirma;

import com.sirma.database.CrudMenu;
import com.sirma.database.DatabaseManager;
import com.sirma.database.DatabaseWriter;
import com.sirma.model.Match;
import com.sirma.model.Player;
import com.sirma.model.Record;
import com.sirma.model.Team;
import com.sirma.parser.CsvParser;
import com.sirma.service.PlayerPairService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1.Initializes the database and creates the tables
        DatabaseManager.initDatabase();

        // 2.Parses CSV files
        CsvParser csvParser = new CsvParser();

        List<Team> teams = csvParser.parseTeams("csv/teams.csv");
        List<Player> players = csvParser.parsePlayers("csv/players.csv");
        List<Match> matches = csvParser.parseMatches("csv/matches.csv");
        List<Record> records = csvParser.parseRecords("csv/records.csv", matches);

        // 3.Saves data to the database
        DatabaseWriter dbWriter = new DatabaseWriter();
        dbWriter.insertTeams(teams);
        dbWriter.insertPlayers(players);
        dbWriter.insertMatches(matches);
        dbWriter.insertRecords(records);

        // 4.Calculates the pairs of players
        PlayerPairService service = new PlayerPairService(records);
        service.calculate();

        //System.out.println("only 1 top pair");
        //System.out.println(service.getTopPair());

        // 5.Print top player pairs
        //System.out.println("Player 1 ID, Player 2 ID, Total Minutes");
        //System.out.println("Match ID, Minutes Together");
        List<String> topPairs = service.getTopPairs();
        for (String pair : topPairs) {
            System.out.println(pair);
        }

        // 6.Start CRUD menu
        CrudMenu crudMenu = new CrudMenu();
        crudMenu.start();
    }
}