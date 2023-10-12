package com.example.cs2340b_team29;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerViewModelTest {
    @Test
    public void testNegativeScore() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.setScore(1000);
        playerViewModel.changeScore(-1001);
        assertEquals(0, playerViewModel.getPlayer().getScore());
    }


    @Test
    public void testIncrementScore() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.setScore(1000);
        playerViewModel.changeScore(10);
        assertEquals(1010, playerViewModel.getPlayer().getScore());
    }

    @Test
    public void testDecrementScore() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.setScore(1000);
        playerViewModel.changeScore(-10);
        assertEquals(990, playerViewModel.getPlayer().getScore());
    }


}
