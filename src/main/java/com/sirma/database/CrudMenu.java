package com.sirma.database;

import com.sirma.model.Match;
import com.sirma.model.Player;
import com.sirma.model.Team;
import com.sirma.util.DateParser;

import java.util.Scanner;

public class CrudMenu {

    private Scanner scanner = new Scanner(System.in);
    private PlayerRepository playerRepository = new PlayerRepository();
    private TeamRepository teamRepository = new TeamRepository();
    private MatchRepository matchRepository = new MatchRepository();

    public void start() {
        System.out.println("********************************");
        System.out.println("Welcome in CRUD FiFa2024 Manager");
        System.out.println("1. Enter");
        System.out.println("2. Exit");
        System.out.println("********************************");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            showMainMenu();
        } else if (choice == 2) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid option! Try again.");
            start();
        }
    }

    private void showMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("********************************");
            System.out.println("MENU:");
            System.out.println("1. Edit Player");
            System.out.println("2. Edit Team");
            System.out.println("3. Edit Match");
            System.out.println("0. Exit");
            System.out.println("********************************");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                showPlayerMenu();
            } else if (choice == 2) {
                showTeamMenu();
            } else if (choice == 3) {
                showMatchMenu();
            } else if (choice == 0) {
                System.out.println("Goodbay!");
                running = false;
            } else {
                System.out.println("Invalid option! Try again.");
            }
        }
    }

    private void showPlayerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("********************************");
            System.out.println("PLAYER MENU:");
            System.out.println("1. Add Player");
            System.out.println("2. View Player by ID");
            System.out.println("3. Update Player");
            System.out.println("4. Delete Player");
            System.out.println("0. Back");
            System.out.println("********************************");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                addPlayer();
            } else if (choice == 2) {
                viewPlayer();
            } else if (choice == 3) {
                updatePlayer();
            } else if (choice == 4) {
                deletePlayer();
            } else if (choice == 0) {
                running = false;
            } else {
                System.out.println("Invalid option! Try again.");
            }
        }
    }

    private void showTeamMenu() {
        boolean running = true;
        while (running) {
            System.out.println("********************************");
            System.out.println("TEAM MENU:");
            System.out.println("1. Add Team");
            System.out.println("2. View Team by ID");
            System.out.println("3. Update Team");
            System.out.println("4. Delete Team");
            System.out.println("0. Back");
            System.out.println("********************************");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                addTeam();
            } else if (choice == 2) {
                viewTeam();
            } else if (choice == 3) {
                updateTeam();
            } else if (choice == 4) {
                deleteTeam();
            } else if (choice == 0) {
                running = false;
            } else {
                System.out.println("Invalid option! Try again.");
            }
        }
    }

    private void showMatchMenu() {
        boolean running = true;
        while (running) {
            System.out.println("********************************");
            System.out.println("MATCH MENU:");
            System.out.println("1. Add Match");
            System.out.println("2. View Match by ID");
            System.out.println("3. Update Match");
            System.out.println("4. Delete Match");
            System.out.println("0. Back");
            System.out.println("********************************");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                addMatch();
            } else if (choice == 2) {
                viewMatch();
            } else if (choice == 3) {
                updateMatch();
            } else if (choice == 4) {
                deleteMatch();
            } else if (choice == 0) {
                running = false;
            } else {
                System.out.println("Invalid option! Try again.");
            }
        }
    }

    //player methods
    private void addPlayer() {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        System.out.print("Enter Team Number: ");
        int teamNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Position: ");
        String position = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Team id: ");
        int teamId = scanner.nextInt();
        scanner.nextLine();

        playerRepository.addPlayer(new Player(id, teamNumber, position, fullName, teamId));
    }

    private void viewPlayer() {
        System.out.print("Enter Player id: ");
        int id = scanner.nextInt();
        playerRepository.viewPlayer(id);
    }

    private void updatePlayer() {
        System.out.print("Enter Player id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Position: ");
        String position = scanner.nextLine();
        System.out.print("Enter New Full Name: ");
        String fullName = scanner.nextLine();

        playerRepository.updatePlayer(id, position, fullName);
    }

    private void deletePlayer() {
        System.out.print("Enter Player id: ");
        int id = scanner.nextInt();
        playerRepository.deletePlayer(id);
    }

    //team methods
    private void addTeam() {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Manager Full Name: ");
        String managerFullName = scanner.nextLine();
        System.out.print("Enter Group: ");
        String group = scanner.nextLine();

        teamRepository.addTeam(new Team(id, name, managerFullName, group));
    }

    private void viewTeam() {
        System.out.print("Enter Team id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        teamRepository.viewTeam(id);
    }

    private void updateTeam() {
        System.out.print("Enter Team id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Manager Full Name: ");
        String managerFullName = scanner.nextLine();

        teamRepository.updateTeam(id, name, managerFullName);
    }

    private void deleteTeam() {
        System.out.print("Enter Team id: ");
        int id = scanner.nextInt();
        teamRepository.deleteTeam(id);
    }

    //match methods
    private void addMatch() {
        System.out.print("Enter Id: ");
        int id = scanner.nextInt();
        System.out.print("Enter team A id: ");
        int aTeamId = scanner.nextInt();
        System.out.print("Enter team B id: ");
        int bTeamId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date: ");
        String date = DateParser.parseToString(scanner.nextLine());
        System.out.print("Enter Score: ");
        String score = scanner.nextLine();

        matchRepository.addMatch(new Match(id, aTeamId, bTeamId, date, score));
    }

    private void viewMatch() {
        System.out.print("Enter match Id: ");
        int id = scanner.nextInt();
        matchRepository.viewMatch(id);
    }

    private void updateMatch() {
        System.out.print("Enter Match Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Score: ");
        String score = scanner.nextLine();
        System.out.print("Enter New Date: ");
        String date = DateParser.parseToString(scanner.nextLine());

        matchRepository.updateMatch(id, score, date);
    }

    private void deleteMatch() {
        System.out.print("Enter match Id: ");
        int id = scanner.nextInt();
        matchRepository.deleteMatch(id);
    }

}
