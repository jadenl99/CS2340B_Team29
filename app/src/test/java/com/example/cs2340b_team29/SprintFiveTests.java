package com.example.cs2340b_team29;
import static org.junit.Assert.assertEquals;

import com.example.cs2340b_team29.collision.EnemyCollisionHandler;
import com.example.cs2340b_team29.collision.KeyCollisionHandler;
import com.example.cs2340b_team29.collision.PlayerAttackCollisionHandler;
import com.example.cs2340b_team29.collision.PowerUpCollisionHandler;
import com.example.cs2340b_team29.collision.WeaponCollisionHandler;
import com.example.cs2340b_team29.model.Enemy;

import com.example.cs2340b_team29.model.Key;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Weapon;
import com.example.cs2340b_team29.powerup.HealthPowerUp;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.powerup.PowerUpBox;
import com.example.cs2340b_team29.powerup.RegenPowerUp;
import com.example.cs2340b_team29.powerup.VaporizeEnemyPowerUp;
import com.example.cs2340b_team29.viewmodel.MapDataViewModel;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;


import org.junit.Test;

import java.util.ArrayList;

public class SprintFiveTests {
    Player player = Player.getPlayer();
    PlayerViewModel playerViewModel = new PlayerViewModel();
    PowerUpCollisionHandler powerUpCollisionHandler =
            new PowerUpCollisionHandler();
    MapData mapData = MapData.getMapData();
    EnemyCollisionHandler enemyCollisionHandler = new EnemyCollisionHandler();
    KeyCollisionHandler keyCollisionHandler = new KeyCollisionHandler();
    WeaponCollisionHandler weaponCollisionHandler =
            new WeaponCollisionHandler();
    PlayerAttackCollisionHandler playerAttackCollisionHandler =
            new PlayerAttackCollisionHandler();
    MapDataViewModel mapDataViewModel = new MapDataViewModel();
    @Test
    public void testPowerUpsHealth() {
        player.setHpLevel(100);
        PowerUp healthPowerUp = new HealthPowerUp(5, 5);
        powerUpCollisionHandler.onCollision(player, healthPowerUp,
                new MoveDown());

        player.updateBuffs();
        assertEquals(player.getHP(), 120);
        // ensure the powerup update does not happen twice
        player.updateBuffs();
        assertEquals(player.getHP(), 120);
        assertEquals(healthPowerUp.getVisible(), false);

    }

    @Test
    public void testPowerUpsRegen() {
        player.setHpLevel(100);
        PowerUp regenPowerUp = new RegenPowerUp(6, 6);
        powerUpCollisionHandler.onCollision(player, regenPowerUp,
                new MoveDown());

        player.updateBuffs();
        assertEquals(player.getHP(), 101);
        // should update every tick
        player.updateBuffs();
        assertEquals(player.getHP(), 102);
        assertEquals(regenPowerUp.getVisible(), false);
    }

    @Test
    public void testPowerUpsVaporize() {
        player.setHpLevel(100);
        mapData.setLevel(2);
        PowerUp vaporizePowerUp = new VaporizeEnemyPowerUp(7, 7);
        powerUpCollisionHandler.onCollision(player, vaporizePowerUp,
                new MoveDown());
        player.updateBuffs();
        ArrayList<Enemy> enemies = mapData.getEnemies(2);
        assertEquals(enemies.get(0).getVisible(), false);
        assertEquals(enemies.get(1).getVisible(), true);
        player.updateBuffs();
        // ensures no double update occurs
        assertEquals(enemies.get(0).getVisible(), false);
        assertEquals(enemies.get(1).getVisible(), true);
    }

    @Test
    public void testPowerUpsRegenAndHealth() {
        player.setHpLevel(100);
        player.setPowerUpBox(new PowerUpBox());
        PowerUp regenPowerUp = new RegenPowerUp(6, 6);
        PowerUp healthPowerUp = new HealthPowerUp(5, 5);
        // simulate player picking up regen powerup first
        powerUpCollisionHandler.onCollision(player, regenPowerUp,
                new MoveDown());
        player.updateBuffs();
        assertEquals(player.getHP(), 101);

        //simulate player picking up health powerup second
        powerUpCollisionHandler.onCollision(player, healthPowerUp,
                new MoveDown());

        player.updateBuffs();
        // player's health should increase by 20 + 1 with the combination of
        // the powerups

        assertEquals(player.getHP(), 122);
    }



    @Test
    public void scoreUpdatesEnemyCollision() {
        mapData.setLevel(1);
        mapData.setDifficulty(3);

        ArrayList<Enemy> enemies = mapData.getEnemies(1);
        enemies.get(0).setX(0);
        enemies.get(0).setVisible(true);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setScore(100);
        player.setX(0);
        player.setY(1);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        player.subscribe(enemyCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(40, player.getScore());
        player.unsubscribe(enemyCollisionHandler);

    }

    @Test
    public void testScoreUpdatesAttackSuccessful() {
        mapDataViewModel.resetMapData();
        mapData.setLevel(1);
        mapData.setDifficulty(3);
        ArrayList<Enemy> enemies = mapData.getEnemies(1);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setScore(100);
        player.setX(0);
        player.setY(1);
        player.setHasKnife(true);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        playerAttackCollisionHandler.onCollision(player, enemies.get(0),
                player.getMoveStrategy());
        assertEquals(200, player.getScore());


    }

    @Test
    public void testAttackEnemyWithoutWeapon() {
        mapDataViewModel.resetMapData();
        player.setHasKnife(false);
        player.setHasSword(false);
        mapData.setLevel(1);
        mapData.setDifficulty(3);
        ArrayList<Enemy> enemies = mapData.getEnemies(1);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        enemies.get(0).setVisible(true);
        player.setHpLevel(100);
        player.setScore(100);
        player.setX(0);
        player.setY(1);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        playerAttackCollisionHandler.onCollision(player, enemies.get(0),
                player.getMoveStrategy());
        assertEquals(true, enemies.get(0).getVisible());


    }

    @Test
    public void testAttackEnemyWithWeapon() {
        mapDataViewModel.resetMapData();
        mapData.setLevel(1);
        mapData.setDifficulty(3);
        ArrayList<Enemy> enemies = mapData.getEnemies(1);
        enemies.get(0).setX(0);
        enemies.get(0).setY(1);
        player.setHpLevel(100);
        player.setScore(100);
        player.setX(0);
        player.setY(1);
        player.setHasKnife(true);
        enemies.get(1).setX(10);
        enemies.get(1).setY(10);

        playerAttackCollisionHandler.onCollision(player, enemies.get(0),
                player.getMoveStrategy());
        assertEquals(false, enemies.get(0).getVisible());


    }


    @Test
    public void scoreUpdatesKeyObtained() {
        mapData.setLevel(1);
        mapData.setDifficulty(3);
        Key key = mapData.getKey();
        key.setX(0);
        key.setY(1);

        player.setScore(100);
        player.setX(0);
        player.setY(1);


        player.subscribe(keyCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(150, player.getScore());
        player.unsubscribe(keyCollisionHandler);
    }

    @Test
    public void pickUpWeapon() {
        mapData.setLevel(1);
        mapData.setDifficulty(3);
        ArrayList<Weapon> weapons = mapData.getWeapons(1);
        weapons.get(0).setX(0);
        weapons.get(0).setY(1);

        player.setScore(100);
        player.setX(0);
        player.setY(1);


        player.subscribe(weaponCollisionHandler);

        playerViewModel.checkForCollisions();
        assertEquals(false, weapons.get(0).getVisible());
        assertEquals(true, player.getHasSword());
        player.unsubscribe(weaponCollisionHandler);
    }



}
