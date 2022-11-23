package com.rsakin.sportradar.casestudy;

import java.util.Objects;

public class Match implements Comparable<Match> {

    private final Team home;
    private final Team away;
    private final int matchOrder;
    // When a game starts, it should capture (being initial score 0-0)
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;

    public Match(Team home, Team away, int matchOrder) {
        this.home = home;
        this.away = away;
        this.matchOrder = matchOrder;
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

    public int getMatchOrder() {
        return matchOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return home.equals(match.home) && away.equals(match.away);
    }

    @Override
    public int hashCode() {
        return Objects.hash(home, away);
    }

    @Override
    public String toString() {
        return home.getName() + " " + homeTeamScore + " - " + away.getName() + " " + awayTeamScore;
    }

    @Override
    public int compareTo(Match other) {
        if (this.homeTeamScore + this.awayTeamScore == other.getHomeTeamScore() + other.getAwayTeamScore()) {
            if (this.matchOrder > other.getMatchOrder()) {
                return -1;
            }
            return 1;
        } else if (this.homeTeamScore + this.awayTeamScore > other.getHomeTeamScore() + other.getAwayTeamScore()) {
            return -1;
        }
        return 1;
    }
}