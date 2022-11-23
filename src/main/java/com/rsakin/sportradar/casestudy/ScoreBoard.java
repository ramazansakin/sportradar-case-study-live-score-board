package com.rsakin.sportradar.casestudy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class ScoreBoard {

    // ScoreBoard needs to be singleton
    private static ScoreBoard scoreBoard = null;
    private static Set<Match> matches;
    // hold match number to know match orders for summary of the score board
    private static int MATCH_ORDER = 1;

    // we can limit the names regarding the country names that is involved to the World Cup for the current season
    public static final List<String> VALID_TEAM_NAMES_FOR_CURRENT_SEASON = Arrays.asList(
            "Qatar", "Brazil", "Belgium", "France", "Argentina", "England", "Spain", "Portugal", "Mexico", "Netherlands",
            "Denmark", "Germany", "Uruguay", "Switzerland", "United States", "Croatia", "Senegal", "Iran", "Japan",
            "Morocco", "Serbia", "Poland", "South Korea", "Tunisia", "Cameroon", "Canada", "Turkey", "Italy", "Australia"
    );

    private ScoreBoard() {
        matches = new HashSet<>();
    }

    public static Set<Match> getMatches() {
        return matches;
    }

    public static ScoreBoard getScoreBoard() {
        // Lazy Loading
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    // For clear sequential test cases, we need to clear the board
    public static void clearBoard() {
        getMatches().clear();
    }

    public void startStreaming() {
        System.out.println("Live Score Board Up and Running");
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.nextLine();
        } while (doCommand(command));

    }

    private boolean doCommand(final String commandLine) {
        // parse the commandLine
        // we can also cover every function as command object to implement 'Command Design Pattern'
        // and we can also create a Command functionalInterface and then implements sub classes for each command objects
        // to use functionalities interchangeably depends on the commandLine parameter - 'Strategy Design Pattern'
        // but the new switch operation also maps the command with related implementation
        String[] commandLineParts = commandLine.split(" ");
        try {
            CommandType commandType = CommandType.valueOf(commandLineParts[0].toUpperCase(Locale.ROOT));
            switch (commandType) {
                case START -> startNewMatch(commandLineParts);
                case FINISH -> finishMatch(commandLineParts);
                case UPDATE -> updateScores(commandLineParts);
                case SUMMARY -> getFullSummary();
                case EXIT -> {
                    return exitApp();
                }
            }
        } catch (IllegalArgumentException exp) {
            System.err.println("There is no such command [ " + commandLineParts[0] + " ]");
        }
        return true;
    }

    private boolean exitApp() {
        System.out.println("Application exit! Thanks for watching our board!");
        return false;
    }

    private void finishMatch(final String[] commandLineParts) {
        // Just need to get the home team name to finish and remove the match from scoreboard
        // but we need to be sure there is a match playing for the team
        Team home = new Team(commandLineParts[1]);
        try {
            Match theMatchWithTeam = getMatch(home);
            matches.remove(theMatchWithTeam);
            System.out.println("Match finished [ " + theMatchWithTeam + " ]");
        } catch (RuntimeException exception) {
            System.err.println("This match is not being played at the moment!");
        }
    }

    // Start a new match and add it to score board
    private void startNewMatch(final String[] commandLineParts) {

        if (commandLineParts.length < 3) {
            System.err.println("Need to get 2 teams to start a match!");
            return;
        }
        if (commandLineParts[1].equals(commandLineParts[2])) {
            System.err.println("Need to get different teams to start a match!");
            return;
        }
        // validate the team names
        if (!validateTeamName(commandLineParts[1]) || !validateTeamName(commandLineParts[2])) {
            return;
        }

        Team home = new Team(commandLineParts[1]);
        Team away = new Team(commandLineParts[2]);
        if (findMatch(home).isPresent() || findMatch(away).isPresent()) {
            System.err.println("Can not start again a match with a team that has a match which is being played currently!");
            return;
        }

        // increase match order every new match addition on board to follow the order
        Match newMatch = new Match(home, away, MATCH_ORDER++);
        matches.add(newMatch);
        System.out.println("Match started [ " + newMatch + " ]");
    }

    private void updateScores(final String[] commandLineParts) {
        if (commandLineParts.length < 5) {
            System.err.println("Need to get team names and scores as parameters at least! Usage : update teamA 1 teamB 0");
            return;
        }
        try {
            int homeTeamScore = Integer.parseInt(commandLineParts[2]);
            int awayTeamScore = Integer.parseInt(commandLineParts[4]);

            if (homeTeamScore < 0 || awayTeamScore < 0) {
                System.err.println("Scores can not be negative!");
                return;
            }

            Team home = new Team(commandLineParts[1]);
            Match theMatchWithTeam = getMatch(home);
            theMatchWithTeam.setHomeTeamScore(homeTeamScore);
            theMatchWithTeam.setAwayTeamScore(awayTeamScore);
            System.out.println("Score updated [ " + theMatchWithTeam + " ]");
        } catch (NumberFormatException exception) {
            System.err.println("Need to send score parameters as number!");
        } catch (RuntimeException exception) {
            System.err.println("This match is not being played at the moment!");
        }
    }

    private void getFullSummary() {
        matches.stream().sorted().forEach(System.out::println);
    }

    private Optional<Match> findMatch(final Team team) {
        return matches.stream()
                .filter(match -> match.getHome().equals(team) || match.getAway().equals(team))
                .findFirst();
    }

    private Match getMatch(final Team home) {
        return matches.stream()
                .filter(match -> match.getHome().equals(home) || match.getAway().equals(home))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No match found"));
    }

    // Also we can use a @ValidTeamName annotation to handle all the business inside that
    // but it needs requiring another libraries, so I just want to leave it simple for now
    private boolean validateTeamName(final String teamName) {
        if (!VALID_TEAM_NAMES_FOR_CURRENT_SEASON.contains(teamName)) {
            System.err.println("Not valid team name [ " + teamName + " ]");
            return false;
        }
        return true;
    }

}
