package com.example.cs2340b_team29;



import static org.junit.Assert.assertEquals;

import com.example.cs2340b_team29.collision.WallCollisionHandler;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveUp;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

import org.junit.Test;

public class PlayerViewModelTest {
    PlayerViewModel playerViewModel = new PlayerViewModel();
    Player player = playerViewModel.getPlayer();
    WallCollisionHandler wallCollisionHandler = new WallCollisionHandler();
    MapDataViewModel mapDataViewModel = new MapDataViewModel();

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


    // NEW JUNITS for sprint 3git

    @Test
    public void testMoveLeft() {
        player.setX(100);
        player.setY(100);
        player.setMoveStrategy(new MoveLeft());
        playerViewModel.move();
        assertEquals(100, player.getY());
        assertEquals(99, player.getX());

    }

    @Test
    public void testMoveDown() {
        player.setX(100);
        player.setY(100);
        player.setMoveStrategy(new MoveDown());
        playerViewModel.move();
        assertEquals(101, player.getY());
        assertEquals(100, player.getX());

    }

    @Test
    public void testMoveUp() {
        player.setX(100);
        player.setY(100);
        player.setMoveStrategy(new MoveUp());
        playerViewModel.move();
        assertEquals(99, player.getY());
        assertEquals(100, player.getX());

    }

    @Test
    public void testMoveRight() {
        player.setX(100);
        player.setY(100);
        player.setMoveStrategy(new MoveRight());
        playerViewModel.move();
        assertEquals(100, player.getY());
        assertEquals(101, player.getX());
    }
    @Test
    public void checkCollisionLevel1() {
        mapDataViewModel.getMapData().setLevel(1);
        player.setX(1);
        player.setY(4);
        // After a move down action, Player bumps into a graveyard at (x=1,
        // y=4).

        // Expected behavior is that the Player is returned back to their
        // original position before the movement happened
        player.setMoveStrategy(new MoveDown());
        player.subscribe(wallCollisionHandler);
        playerViewModel.checkForCollisions();
        assertEquals(2, player.getY());
        assertEquals(1, player.getX());
        player.unsubscribe(wallCollisionHandler);

    }
    @Test
    public void checkNoCollisionLevel1() {
        player.setX(4);
        player.setY(4);
        mapDataViewModel.getMapData().setLevel(1);
        player.setMoveStrategy(new MoveDown());
        playerViewModel.move();
        player.subscribe(wallCollisionHandler);
        assertEquals(5, player.getY());
        assertEquals(4, player.getX());
        player.unsubscribe(wallCollisionHandler);
    }

    @Test
    public void checkCollisionLevel1OutOfBounds() {
        mapDataViewModel.getMapData().setLevel(1);
        player.setX(-1);
        player.setY(4);
        // After a move left action, Player moves offscreen

        // Expected behavior is that the Player is returned back to their
        // original position before the movement happened
        player.setMoveStrategy(new MoveLeft());
        player.subscribe(wallCollisionHandler);
        playerViewModel.checkForCollisions();
        assertEquals(4, player.getY());
        assertEquals(0, player.getX());
        player.unsubscribe(wallCollisionHandler);

    }

    @Test
    public void changeLevelFrom1To2AndCollision() {
        mapDataViewModel.getMapData().setLevel(1);
        player.setX(-1);
        player.setY(4);

        player.setMoveStrategy(new MoveLeft());
        player.subscribe(wallCollisionHandler);
        playerViewModel.checkForCollisions();
        assertEquals(4, player.getY());
        assertEquals(1, player.getX());
        mapDataViewModel.getMapData().setLevel(2);
        player.setX(4);
        player.setY(8);
        player.setMoveStrategy(new MoveDown());
        playerViewModel.checkForCollisions();
        assertEquals(6, player.getY());
        assertEquals(4, player.getX());
        player.unsubscribe(wallCollisionHandler);
    }
    @Test
    public void changeLevelFrom2to3() {
        mapDataViewModel.getMapData().setLevel(2);
        player.setX(2);
        player.setY(19);

        player.setMoveStrategy(new MoveLeft());
        player.subscribe(wallCollisionHandler);
        playerViewModel.checkForCollisions();
        assertEquals(19, player.getY());
        assertEquals(2, player.getX());
        mapDataViewModel.getMapData().setLevel(3);
        player.setX(1);
        player.setY(12);
        player.setMoveStrategy(new MoveDown());
        playerViewModel.checkForCollisions();
        assertEquals(11, player.getY());
        assertEquals(1, player.getX());
        player.unsubscribe(wallCollisionHandler);
    }

    @Test
    public void testOutOfBoundsRightSide() {
        mapDataViewModel.getMapData().setLevel(3);
        player.setX(11);
        player.setY(0);
        player.subscribe(wallCollisionHandler);
        player.setMoveStrategy(new MoveRight());
        playerViewModel.checkForCollisions();
        assertEquals(0, player.getY());
        assertEquals(10, player.getX());
    }






}
