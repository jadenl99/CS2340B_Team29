package com.example.cs2340b_team29.powerup;

import com.example.cs2340b_team29.model.Player;


/**
 * Continuously increments player health by one every time
 * updateBuffsToPlayer() is called
 */
public class RegenPowerUpDecorator extends PowerUpDecorator {
    public RegenPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
    }

    @Override
    public void updateBuffsToPlayer(Player player) {
        super.updateBuffsToPlayer(player);
        player.setHpLevel(1 + player.getHP());
    }
}
