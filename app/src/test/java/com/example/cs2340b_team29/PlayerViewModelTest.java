package com.example.cs2340b_team29;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerViewModelTest {
    @Test
    public void testNegativeScore() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.changeScore(1);
        assertEquals(101, playerViewModel.getPlayer().getScore());
        playerViewModel.changeScore(-101);
        assertEquals(0, playerViewModel.getPlayer().getScore());
        playerViewModel.changeScore(-101);
        assertEquals(0, playerViewModel.getPlayer().getScore());
    }


    @Test
    public void testRestartScore() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.changeScore(1);
        assertEquals(101, playerViewModel.getPlayer().getScore());
        playerViewModel.restartGamePlayerData();
        assertEquals(100, playerViewModel.getPlayer().getScore());

    }

    @Test
    public void testPause() {
        L1View level1 = new L1View();

    }

    @Test
    public void testDifficulty() {

    }

}
