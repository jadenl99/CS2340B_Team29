package com.example.cs2340b_team29.powerup;

import com.example.cs2340b_team29.model.Player;

public class AttackPowerUpDecorator extends PowerUpDecorator {

    public AttackPowerUpDecorator(BasePowerUpBox powerUpBox) {
        super(powerUpBox);
    }

    @Override
    public void updateBuffsToPlayer(Player player) {
    }
}
