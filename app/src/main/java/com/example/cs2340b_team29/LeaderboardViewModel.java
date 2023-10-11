package com.example.cs2340b_team29;
import java.util.ArrayList;
import androidx.lifecycle.ViewModel;
import java.util.Collections;
import java.util.Comparator;


public class LeaderboardViewModel extends ViewModel {
    private Leaderboard leaderboard;

    public LeaderboardViewModel() {
        leaderboard = Leaderboard.getLeaderboard();
    }

    public void addLatestAttempt(LeaderboardEntry newAttempt){
        leaderboard.getAttempts().add(newAttempt);
    }

    public void sortAttempts(){
        ArrayList <LeaderboardEntry> attempts = leaderboard.getAttempts();
        Collections.sort(attempts, new Comparator<LeaderboardEntry>() {
            public int compare(LeaderboardEntry a, LeaderboardEntry b) {
                return b.getScore() - a.getScore();
            }
        });
    }

}
