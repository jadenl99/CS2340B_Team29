package com.example.cs2340b_team29;
import static org.junit.Assert.assertEquals;
import com.example.cs2340b_team29.collision.PowerUpCollisionHandler;
import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.powerup.HealthPowerUp;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.powerup.PowerUpBox;
import com.example.cs2340b_team29.powerup.RegenPowerUp;
import com.example.cs2340b_team29.powerup.VaporizeEnemyPowerUp;
import com.example.cs2340b_team29.viewmodel.MoveDown;
import com.example.cs2340b_team29.viewmodel.PlayerViewModel;
import com.example.cs2340b_team29.powerup.AttackPowerUpDecorator;

import org.junit.Test;

import java.util.ArrayList;

public class SprintFiveTests {
    Player player = Player.getPlayer();
    PlayerViewModel playerViewModel = new PlayerViewModel();
    PowerUpCollisionHandler powerUpCollisionHandler =
            new PowerUpCollisionHandler();
    MapData mapData = MapData.getMapData();

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
}
