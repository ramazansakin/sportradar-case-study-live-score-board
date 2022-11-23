package com.rsakin.sportradar.casestudy;

import java.util.Scanner;
import java.util.Set;

public class ScoreBoard {

    // ScoreBoard needs to be singleton
    private static ScoreBoard scoreBoard = null;
    private Set<Match> matches;

    private ScoreBoard() {
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
                System.out.println("Match started [ " + commandLineParts[1] + " 0 - " + commandLineParts[2] + " 0 ]");
            } else if (commandLineParts[0].equals("update")) {
                System.out.println("Score updated [ " + commandLineParts[1] + " " + commandLineParts[2]
                        + " - " + commandLineParts[3] + " " + commandLineParts[4] + " ]");
            }
            commandLine = in.nextLine();
        }
    }


}
