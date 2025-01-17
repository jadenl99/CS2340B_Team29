package com.example.cs2340b_team29.model;


import java.util.Date;

public class LeaderboardEntry {
    private String name;
    private int score;
    private String date;
    private Date dateTime;
    private int avatarId;

    public LeaderboardEntry(String name, int score, Date dateTime) {
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
    public Date getDateTime() {
        return dateTime;
    }
}
