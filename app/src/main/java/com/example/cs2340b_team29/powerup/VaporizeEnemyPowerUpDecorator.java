package com.example.cs2340b_team29.powerup;

import com.example.cs2340b_team29.model.Enemy;
import com.example.cs2340b_team29.model.MapData;
import com.example.cs2340b_team29.model.Player;

import java.util.ArrayList;

public class VaporizeEnemyPowerUpDecorator extends PowerUpDecorator {
    private boolean isUsed;

    public VaporizeEnemyPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
        isUsed = false;
    }

    /**
     * Make an enemy magically disappear, if there are any visible.
     * @param player the player of the game
     */
    @Override
    public void updateBuffsToPlayer(Player player) {
        if (isUsed) {
            return;
        }
        int level = MapData.getMapData().getLevel();
        ArrayList<Enemy> enemies = MapData.getMapData().getEnemies(level);

        for (Enemy enemy : enemies) {
            if (enemy.getVisible()) {
                enemy.setVisible(false);
                return;
            }
        }
    }
}
