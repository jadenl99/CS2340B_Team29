package com.example.cs2340b_team29.powerup;
import com.example.cs2340b_team29.model.Player;
import com.badlogic.gdx.utils.compression.lzma.Base;
/**
 * Continuously increments player health by one every time
 * updateBuffsToPlayer() is called
 */

public class RegenPowerUpDecorator extends PowerUpDecorator {
    private int hpRestored;
  
    public RegenPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
        hpRestored = 0;
    }

    @Override
    public void updateBuffsToPlayer(Player player) {
        super.updateBuffsToPlayer(player);
        if (hpRestored < 10) {
            player.setHpLevel(1 + player.getHP());
            hpRestored++;
        }
    }
}
