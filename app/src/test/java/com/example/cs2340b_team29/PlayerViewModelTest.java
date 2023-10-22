package com.example.cs2340b_team29;



import static org.junit.Assert.assertEquals;

import com.example.cs2340b_team29.collision.WallCollisionHandler;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

import org.junit.Test;

public class PlayerViewModelTest {
    PlayerViewModel playerViewModel = new PlayerViewModel();
    Player player = playerViewModel.getPlayer();
    WallCollisionHandler wallCollisionHandler = new WallCollisionHandler();

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

    @Test
    public void testVariousScoreChanges() {
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.setScore(1000);
        playerViewModel.changeScore(-10);
        playerViewModel.changeScore(-1);
        playerViewModel.changeScore(100);
        playerViewModel.changeScore(-100);
        assertEquals(989, playerViewModel.getPlayer().getScore());
    }




    @Test
    public void setPlayerData() {
        Player player = Player.getPlayer();
        PlayerViewModel playerViewModel = new PlayerViewModel();
        playerViewModel.setPlayerData(1000, "hi", 1);
        assertEquals(1000, player.getScore());
        assertEquals("hi", player.getPlayerName());
        assertEquals(1, player.getIdAvatar());
    }

    // NEW JUNITS
    @Test
    public void checkCollisionLevel1() {
        player.setLevel(1);
        player.setX(1);
        player.setY(4);
        // After a move down action, Player bumps into a graveyard at (x=1,
        // y=4).

        // Expected behavior is that the Player is returned back to their
        // original position before the movement happened
        playerViewModel.setMoveStrategy(new MoveDown());
        player.subscribe(wallCollisionHandler);
        playerViewModel.checkForCollisions();
        assertEquals(3, player.getY());
        assertEquals(1, player.getX());
        player.unsubscribe(wallCollisionHandler);

    }

    @Test
    public void checkCollisionLevel1OutOfBounds() {
        player.setLevel(1);
        player.setX(-1);
        player.setY(4);
        // After a move left action, Player moves offscreen

        // Expected behavior is that the Player is returned back to their
        // original position before the movement happened
        playerViewModel.setMoveStrategy(new MoveLeft());
        player.subscribe(wallCollisionHandler);
        playerViewModel.checkForCollisions();
        assertEquals(4, player.getY());
        assertEquals(0, player.getX());
        player.unsubscribe(wallCollisionHandler);

    }




}
