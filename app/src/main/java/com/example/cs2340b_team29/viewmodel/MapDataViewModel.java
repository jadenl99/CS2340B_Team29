package com.example.cs2340b_team29.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.Key;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;
import com.example.cs2340b_team29.model.Weapon;
import com.example.cs2340b_team29.powerup.PowerUp;
import com.example.cs2340b_team29.powerup.PowerUpBox;

import java.util.ArrayList;


public class MapDataViewModel extends ViewModel {
    private MapData mapData = MapData.getMapData();
    private Player player = Player.getPlayer();

    public void setMapData(int level) {
        mapData.setDifficulty(level);
        // logic for setting the initial enemy locations can be here

    }

    public MapData getMapData() {
        return mapData;
    }

    public void resetMapData() {
        player.setPowerUpBox(new PowerUpBox());
        ArrayList<PowerUp> powerUp1 = mapData.getPowerUp(1);
        ArrayList<PowerUp> powerUp2 = mapData.getPowerUp(2);
        ArrayList<PowerUp> powerUp3 = mapData.getPowerUp(3);
        for (PowerUp powerUp : powerUp1) {
            powerUp.setVisible(true);
        }

        for (PowerUp powerUp : powerUp2) {
            powerUp.setVisible(true);
        }

        for (PowerUp powerUp : powerUp3) {
            powerUp.setVisible(true);
        }
        ArrayList<Enemy> enemy1 = mapData.getEnemies(1);
        ArrayList<Enemy> enemy2 = mapData.getEnemies(2);
        ArrayList<Enemy> enemy3 = mapData.getEnemies(3);

        for (Enemy enemy : enemy1) {
            enemy.setVisible(true);
        }

        for (Enemy enemy : enemy2) {
            enemy.setVisible(true);
        }

        for (Enemy enemy : enemy3) {
            enemy.setVisible(true);
        }

        ArrayList<Weapon> weapons = mapData.getAllWeapons();
        for (Weapon weapon : weapons) {
            weapon.setVisible(true);
        }

        ArrayList<Key> keys = mapData.getKeys();
        for (Key key : keys) {
            key.setVisible(true);
        }

        player.setHasKnife(false);
        player.setHasSword(false);




    }

}
