package com.rsakin.sportradar.casestudy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ScoreBoard {

    // ScoreBoard needs to be singleton
    private static ScoreBoard scoreBoard = null;
    private Set<Match> matches;

    // we can limit the names regarding the country names that is involved to the World Cup for the current season
    public static final List<String> VALID_TEAM_NAMES_FOR_CURRENT_SEASON = Arrays.asList(
            "Qatar", "Brazil", "Belgium", "France", "Argentina", "England", "Spain", "Portugal", "Mexico", "Netherlands",
            "Denmark", "Germany", "Uruguay", "Switzerland", "United States", "Croatia", "Senegal", "Iran", "Japan",
            "Morocco", "Serbia", "Poland", "South Korea", "Tunisia", "Cameroon", "Canada", "Turkey", "Italy", "Australia"
    );

    private ScoreBoard() {
        matches = new HashSet<>();
    }

    public static ScoreBoard getScoreBoard() {
        // Lazy Loading
        if (scoreBoard == null) {
            scoreBoard = new ScoreBoard();
        }
        return scoreBoard;
    }

    public void startStreaming() {
        System.out.println("Live Score Board Up and Running");
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        String commandLine = in.nextLine();
        // we need to make a loop to get sequential commands and need to define an exit command
        while (!commandLine.equals("exit")) {
            String[] commandLineParts = commandLine.split(" ");
            if (commandLineParts[0].equals("start")) {
                startNewMatch(commandLineParts);
            } else if (commandLineParts[0].equals("update")) {
                updateScores(commandLineParts);
            }
            commandLine = in.nextLine();
        }
    }

    // Start a new match and add it to score board
    private void startNewMatch(final String[] commandLineParts) {
        Team home = new Team(commandLineParts[1]);
        Team away = new Team(commandLineParts[2]);
        // validate the team names
        if (!validateTeamName(commandLineParts[1]) || !validateTeamName(commandLineParts[2])) {
            return;
        }

        // increase match order every new match addition on board to follow the order
        Match newMatch = new Match(home, away);
        matches.add(newMatch);
        System.out.println("Match started [ " + newMatch + " ]");
    }

    private void updateScores(final String[] commandLineParts) {
        Team home = new Team(commandLineParts[1]);
        int homeTeamScore = Integer.parseInt(commandLineParts[2]);
        int awayTeamScore = Integer.parseInt(commandLineParts[4]);

        if (homeTeamScore < 0 || awayTeamScore < 0) {
            System.err.println("Scores can not be negative!");
            return;
        }

        try {
            Match theMatchWithTeam = getTheMatchByHomeTeam(home);
            theMatchWithTeam.setHomeTeamScore(homeTeamScore);
            theMatchWithTeam.setAwayTeamScore(awayTeamScore);
            System.out.println("Score updated [ " + theMatchWithTeam + " ]");
        } catch (RuntimeException exception) {
            System.err.println("This match is not being played at the moment!");
        }
    }

    private Match getTheMatchByHomeTeam(final Team home) {
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
