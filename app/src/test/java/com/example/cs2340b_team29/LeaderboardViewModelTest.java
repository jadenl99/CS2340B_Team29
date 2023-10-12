package com.example.cs2340b_team29;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;

public class LeaderboardViewModelTest {
    @Test
    public void testAddAttempt() {
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        LeaderboardEntry newEntry = new LeaderboardEntry("Name", 100, new Date());
        leaderboardViewModel.addLatestAttempt(newEntry);
        assertEquals(1, leaderboardViewModel.getLeaderboard().getAttempts().size());
    }
    @Test
    public void testSortAttempts() {
        LeaderboardEntry entry1 = new LeaderboardEntry("Name", 100, new Date());
        LeaderboardEntry entry2 = new LeaderboardEntry("Name", 200, new Date());
        LeaderboardEntry entry3 = new LeaderboardEntry("Name", 300, new Date());
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.addLatestAttempt(entry1);
        leaderboardViewModel.addLatestAttempt(entry2);
        leaderboardViewModel.addLatestAttempt(entry3);
        leaderboardViewModel.sortAttempts();
        assertEquals(300, leaderboardViewModel.getLeaderboard().getAttempts().get(0).getScore());
        assertEquals(200, leaderboardViewModel.getLeaderboard().getAttempts().get(1).getScore());
        assertEquals(100, leaderboardViewModel.getLeaderboard().getAttempts().get(2).getScore());
    }
    }

