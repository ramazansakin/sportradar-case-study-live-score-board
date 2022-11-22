package com.rsakin.sportradar.casestudy;

public class FCLiveScoreBoardApp {

    public static void main(String[] args) {
        // Init and start score board screening
        ScoreBoard scoreBoard = ScoreBoard.getScoreBoard();
        scoreBoard.startStreaming();
    }

}
