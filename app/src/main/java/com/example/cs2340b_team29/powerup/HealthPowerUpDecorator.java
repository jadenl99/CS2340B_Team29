package com.example.cs2340b_team29.powerup;

import com.example.cs2340b_team29.model.Player;


/**
 * Increase Player's HP by 20. A one time event.    
 */
public class HealthPowerUpDecorator extends PowerUpDecorator {
    private boolean isUsed;
    public HealthPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
        isUsed = false;
    }

    @Override
    public void updateBuffsToPlayer(Player player) {
        super.updateBuffsToPlayer(player);
        if (!isUsed) {
            isUsed = true;
            player.setHpLevel(player.getHP() + 20);
        }

    }
}
