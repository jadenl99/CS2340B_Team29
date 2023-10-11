package com.example.cs2340b_team29;

public class LeaderboardEntry {
    private String name;
    private int score;
    private long dateTime;
    private int avatarId;

    public LeaderboardEntry(String name, int score, long dateTime) {
        this.name = name;
        this.score = score;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public long getDateTime() {
        return dateTime;
    }
}
