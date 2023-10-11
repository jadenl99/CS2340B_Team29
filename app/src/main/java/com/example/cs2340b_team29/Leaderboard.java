package com.example.cs2340b_team29;

import android.os.Bundle;
import android.widget.TextView;

import java.util.*;

public class Leaderboard {
    private volatile static Leaderboard uniqueInstance;
    private ArrayList<Player> players;
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
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addLatestPlayer(Player newPlayer){
        players.add(newPlayer);
    }
    
    public void sortScores(){
       ArrayList<Player> sortedPlayers = new ArrayList<>(players.size());
       for(int i = 0; i < players.size(); i++) {
           int highestPlayerIndex = 0;
           for (int j = 1; j < players.size(); j++) {
               if (players.get(j).getScore() > players.get(highestPlayerIndex).getScore()) {
                   highestPlayerIndex = j;
               }
           }
           sortedPlayers.add(players.get(highestPlayerIndex));
           players.remove(highestPlayerIndex);
       }
       players = sortedPlayers;
    }


}
