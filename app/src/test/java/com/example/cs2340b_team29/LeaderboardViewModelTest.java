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

    @Test
    public void testSortAttempts5() {
        LeaderboardEntry entry1 = new LeaderboardEntry("Name", 100, new Date());
        LeaderboardEntry entry2 = new LeaderboardEntry("Name", 200, new Date());
        LeaderboardEntry entry3 = new LeaderboardEntry("Name", 300, new Date());
        LeaderboardEntry entry4 = new LeaderboardEntry("Name", 500, new Date());
        LeaderboardEntry entry5 = new LeaderboardEntry("Name", 400, new Date());
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.addLatestAttempt(entry1);
        leaderboardViewModel.addLatestAttempt(entry2);
        leaderboardViewModel.addLatestAttempt(entry3);
        leaderboardViewModel.addLatestAttempt(entry4);
        leaderboardViewModel.addLatestAttempt(entry5);
        leaderboardViewModel.sortAttempts();
        assertEquals(500,
                leaderboardViewModel.getLeaderboard().getAttempts().get(0).getScore());
        assertEquals(400,
                leaderboardViewModel.getLeaderboard().getAttempts().get(1).getScore());
        assertEquals(300,
                leaderboardViewModel.getLeaderboard().getAttempts().get(2).getScore());
        assertEquals(200,
                leaderboardViewModel.getLeaderboard().getAttempts().get(3).getScore());
        assertEquals(100,
                leaderboardViewModel.getLeaderboard().getAttempts().get(4).getScore());
    }

    @Test
    public void testSortAttemptsMoreThan5() {
        LeaderboardEntry entry1 = new LeaderboardEntry("Name", 100, new Date());
        LeaderboardEntry entry2 = new LeaderboardEntry("Name", 200, new Date());
        LeaderboardEntry entry3 = new LeaderboardEntry("Name", 300, new Date());
        LeaderboardEntry entry4 = new LeaderboardEntry("Name", 500, new Date());
        LeaderboardEntry entry5 = new LeaderboardEntry("Name", 400, new Date());
        LeaderboardEntry entry6 = new LeaderboardEntry("Name", 2000,
                new Date());
        LeaderboardEntry entry7 = new LeaderboardEntry("Name", 2001,
                new Date());
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.addLatestAttempt(entry1);
        leaderboardViewModel.addLatestAttempt(entry2);
        leaderboardViewModel.addLatestAttempt(entry3);
        leaderboardViewModel.addLatestAttempt(entry4);
        leaderboardViewModel.addLatestAttempt(entry5);
        leaderboardViewModel.addLatestAttempt(entry6);
        leaderboardViewModel.addLatestAttempt(entry7);
        leaderboardViewModel.sortAttempts();
        assertEquals(2001,
                leaderboardViewModel.getLeaderboard().getAttempts().get(0).getScore());
        assertEquals(2000,
                leaderboardViewModel.getLeaderboard().getAttempts().get(1).getScore());
        assertEquals(500,
                leaderboardViewModel.getLeaderboard().getAttempts().get(2).getScore());
        assertEquals(400,
                leaderboardViewModel.getLeaderboard().getAttempts().get(3).getScore());
        assertEquals(300,
                leaderboardViewModel.getLeaderboard().getAttempts().get(4).getScore());
    }

    @Test
    public void testLeaderboardNamesCorrect() {
        LeaderboardEntry entry1 = new LeaderboardEntry("A", 100,
                new Date());
        LeaderboardEntry entry2 = new LeaderboardEntry("B", 200, new Date());
        LeaderboardEntry entry3 = new LeaderboardEntry("C", 300, new Date());
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        leaderboardViewModel.addLatestAttempt(entry1);
        leaderboardViewModel.addLatestAttempt(entry2);
        leaderboardViewModel.addLatestAttempt(entry3);
        leaderboardViewModel.sortAttempts();
        assertEquals("C",
                leaderboardViewModel.getLeaderboard().getAttempts().get(0).getName());
        assertEquals("B",
                leaderboardViewModel.getLeaderboard().getAttempts().get(1).getName());
        assertEquals("A",
                leaderboardViewModel.getLeaderboard().getAttempts().get(2).getName());
    }
}

