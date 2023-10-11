package com.example.cs2340b_team29;

import java.util.*;

public class Leaderboard {
    private volatile static Leaderboard uniqueInstance;
    private ArrayList<LeaderboardEntry> attempts;
    private Leaderboard(){}
    public static Leaderboard getInstance(){
        if (uniqueInstance == null) {
            synchronized (Leaderboard.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Leaderboard();
                }
            }
        }
        return uniqueInstance;
    }
    public ArrayList<LeaderboardEntry> getAttempts() {
        return attempts;
    }

    public void addLatestEntry(LeaderboardEntry entry) {
        //TODO
    }

    public void sortScores() {

    }


}