package com.example.cs2340b_team29.powerup;

import com.example.cs2340b_team29.model.Player;

public class HealthPowerUpDecorator extends PowerUpDecorator {

    public HealthPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
    }

    @Override
    public void updateBuffsToPlayer(Player player) {
        player.setHpLevel(player.getHP() + 20);
    }
}
