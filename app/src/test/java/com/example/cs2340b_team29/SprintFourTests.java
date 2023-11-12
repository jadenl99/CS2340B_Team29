package com.example.cs2340b_team29;



import static org.junit.Assert.assertEquals;

import com.example.cs2340b_team29.collision.EnemyCollisionHandler;
import com.example.cs2340b_team29.collision.WallCollisionHandler;
import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.EnemyFactory;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Spider;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.MoveLeft;
import com.example.cs2340b_team29.viewmodel.MoveRight;
import com.example.cs2340b_team29.viewmodel.MoveUp;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;

import org.junit.Test;

import java.util.ArrayList;

public class SprintFourTests {
    PlayerViewModel playerViewModel = new PlayerViewModel();
    Player player = playerViewModel.getPlayer();
    WallCollisionHandler wallCollisionHandler = new WallCollisionHandler();
    MapDataViewModel mapDataViewModel = new MapDataViewModel();
    EnemyCollisionHandler enemyCollisionHandler = new EnemyCollisionHandler();

    /**
     * Test player health does not reach below 0
     */
    @Test
    public void testNoNegativeHP() {
        Enemy spider = EnemyFactory.createEnemy("spider");
        player.setHpLevel(1);
        spider.attack(player, 2);
        assertEquals(0, player.getHP());
    }

    /**
     * Test if collision registers with enemy, when they are close to each other
     */
    @Test
    public void testCollisionRegistersWithEnemy1() {
        Enemy spider = EnemyFactory.createEnemy("spider");
        player.setHpLevel(100);
        player.setX(0);
        player.setY(1);
        spider.setX(0);
        spider.setY(2);
        mapDataViewModel.getMapData().setDifficulty(3);
        assertEquals(true, playerViewModel.checkAdjacentCollision(player,
                spider));
    }

    /**
     * Check collision registers when player and enemy are right on top of
     * each other
     */
    @Test
    public void testCollisionRegistersWithEnemy2() {
        Enemy spider = EnemyFactory.createEnemy("spider");
        player.setHpLevel(100);
        player.setX(0);
        player.setY(1);
        spider.setX(0);
        spider.setY(1);
        mapDataViewModel.getMapData().setDifficulty(3);
        assertEquals(true, playerViewModel.checkCollision(player,
                spider));
    }


    /**
     * Test that HP updates correctly in easy mode
     */
    @Test
    public void handleCollisionEasy() {
        Enemy wolf = EnemyFactory.createEnemy("wolf");
        player.setHpLevel(100);
        wolf.attack(player, 1);
        assertEquals(98, player.getHP());
    }

    /**
     * Test that HP updates correctly in medium mode
     */
    @Test
    public void handleCollisionMedium() {
        Enemy wolf = EnemyFactory.createEnemy("wolf");
        player.setHpLevel(100);
        wolf.attack(player, 2);
        assertEquals(95, player.getHP());
    }

    /**
     * Test that HP updates correctly in hard mode
     */
    @Test
    public void handleCollisionHard() {
        Enemy wolf = EnemyFactory.createEnemy("wolf");
        player.setHpLevel(100);
        wolf.attack(player, 3);
        assertEquals(93, player.getHP());
    }

    /**
     * Test the integrity of the observer pattern for level 1.
     */
    @Test
    public void handleCollisionsLevel1() {
        MapData mapData = mapDataViewModel.getMapData();
        mapData.setLevel(1);
        mapData.setDifficulty(2);
        ArrayList<Enemy> enemies = mapData.getEnemies(1);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setX(0);
        player.setY(1);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        player.subscribe(enemyCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(96, player.getHP());
        player.unsubscribe(enemyCollisionHandler);

    }

    /**
     * Test the integrity of the observer pattern for level 2.
     */
    @Test
    public void handleCollisionsLevel2() {
        MapData mapData = mapDataViewModel.getMapData();
        mapData.setLevel(2);
        mapData.setDifficulty(2);
        ArrayList<Enemy> enemies = mapData.getEnemies(2);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setX(10);
        player.setY(10);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        player.subscribe(enemyCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(97, player.getHP());
        player.unsubscribe(enemyCollisionHandler);
    }

    /**
     * Test the integrity of the observer pattern for level 3.
     */
    @Test
    public void handleCollisionsLevel3() {
        MapData mapData = mapDataViewModel.getMapData();
        mapData.setLevel(3);
        mapData.setDifficulty(2);
        ArrayList<Enemy> enemies = mapData.getEnemies(3);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setX(10);
        player.setY(10);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        player.subscribe(enemyCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(98, player.getHP());
        player.unsubscribe(enemyCollisionHandler);
    }

    /**
     * Tests on level 1 enemies that a collision does not happen if the
     * player is far away from enemies.
     */
    /**
     * Test the integrity of the observer pattern for level 1.
     */
    @Test
    public void handleCollisionsNoCollisionRegistered() {
        MapData mapData = mapDataViewModel.getMapData();
        mapData.setLevel(1);
        mapData.setDifficulty(2);
        ArrayList<Enemy> enemies = mapData.getEnemies(1);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setX(0);
        player.setY(3);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        player.subscribe(enemyCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(100, player.getHP());
        player.unsubscribe(enemyCollisionHandler);

    }








}
