package com.rsakin.sportradar.casestudy;

public class Match {

    // we need to be sure the teams defined at the first time and never changed
    private final Team home;
    private final Team away;
    // When a game starts, it should capture (being initial score 0-0)
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public String toString() {
        return home.getName() + " " + homeTeamScore + " - " + away.getName() + " " + awayTeamScore;
    }


}
