package com.rsakin.sportradar.casestudy;

import java.util.Scanner;

public class ScoreBoard {

    // ScoreBoard needs to be singleton
    private static ScoreBoard scoreBoard = null;

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

        String[] commandLineParts = commandLine.split(" ");
        if (commandLineParts[0].equals("start")) {
            System.out.println("Match started [ " + commandLineParts[1] + " 0 - " + commandLineParts[2] + " 0 ]");
        }

    }
}
