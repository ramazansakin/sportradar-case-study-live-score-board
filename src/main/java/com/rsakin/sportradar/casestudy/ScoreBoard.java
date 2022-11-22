package com.rsakin.sportradar.casestudy;

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
    }
}
