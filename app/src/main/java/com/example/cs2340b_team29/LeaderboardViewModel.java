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

    public void addLatestAttempt(LeaderboardEntry newAttempt) {
        leaderboard.getAttempts().add(newAttempt);
    }

    public void sortAttempts() {
        ArrayList<LeaderboardEntry> attempts = leaderboard.getAttempts();
        class ScoreComparator implements Comparator<LeaderboardEntry> {
            public int compare(LeaderboardEntry obj1, LeaderboardEntry obj2) {
                return Integer.compare(obj1.getScore(), obj2.getScore());
            }
        }
        Collections.sort(attempts, new ScoreComparator());
        Collections.reverse(attempts);
    }

    public Leaderboard getLeaderboard() {
        if (leaderboard.getAttempts().size() > 0) {
            return leaderboard;
        } else {
            throw new NullPointerException("No attempts made yet");
        }
    }

}
